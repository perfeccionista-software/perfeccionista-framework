package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.exceptions.attachments.WebExtractorDescriptionAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebFilterBuilderDescriptionAttachmentEntry;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.Web.emptyWebListFilter;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SIZE_ELEMENTS_METHOD;

public class WebListMultipleIndexedResult<R, T extends WebBlock> implements WebMultipleIndexedResult<R, WebList<T>> {

    private final WebList<T> element;
    private final WebListFilterBuilder<T> filterBuilder;
    private final WebListBlockValueExtractor<R, T> extractor;

    private WebListMultipleIndexedResult(WebList<T> element,
                                         WebListFilterBuilder<T> filterBuilder,
                                         WebListBlockValueExtractor<R, T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <R, T extends WebBlock> WebListMultipleIndexedResult<R, T> of(@NotNull WebList<T> element,
                                                                                @NotNull WebListFilterBuilder<T> filterBuilder,
                                                                                @NotNull WebListBlockValueExtractor<R, T> extractor) {
        return new WebListMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <R, T extends WebBlock> WebListMultipleIndexedResult<R, T> of(@NotNull WebList<T> element,
                                                                                @NotNull WebListBlockValueExtractor<R, T> extractor) {
        return new WebListMultipleIndexedResult<>(element, emptyWebListFilter(), extractor);
    }

    @Override
    public @NotNull WebList<T> getElement() {
        return element;
    }

    @Override
    public Map<Integer, R> getResults() {
        var elementName = element.getElementIdentifier().getLastUsedName();
        var invocationInfo = getterInvocation(GET_EXTRACTED_VALUES_METHOD, elementName)
                .addAttachmentEntry(WebFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(WebExtractorDescriptionAttachmentEntry.of(extractor));
        return runCheck(invocationInfo, () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        WebListFilter<T> webListFilter = filterBuilder.build(element);
        var elementName = element.getElementIdentifier().getLastUsedName();
        var invocationInfo = getterInvocation(GET_SIZE_ELEMENTS_METHOD, elementName)
                .addAttachmentEntry(WebFilterBuilderDescriptionAttachmentEntry.of(filterBuilder));
        return runCheck(invocationInfo, () -> webListFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public WebMultipleIndexedResult<R, WebList<T>> should(WebMultipleIndexedResultMatcher<R> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull WebSingleIndexedResult<R, WebList<T>> singleResult() {
        return WebListSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}
