package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.exceptions.attachments.WebExtractorDescriptionAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebFilterBuilderDescriptionAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilter;
import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilterBuilder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.Web.emptyWebTextBlockFilter;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SIZE_ELEMENTS_METHOD;

public class WebTextListMultipleIndexedResult<T> implements WebMultipleIndexedResult<T, WebTextList> {

    private final WebTextList element;
    private final WebTextBlockFilterBuilder filterBuilder;
    private final WebTextListBlockValueExtractor<T> extractor;

    private WebTextListMultipleIndexedResult(WebTextList element,
                                             WebTextBlockFilterBuilder filterBuilder,
                                             WebTextListBlockValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> WebTextListMultipleIndexedResult<T> of(@NotNull WebTextList element,
                                                             @NotNull WebTextBlockFilterBuilder filterBuilder,
                                                             @NotNull WebTextListBlockValueExtractor<T> extractor) {
        return new WebTextListMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> WebTextListMultipleIndexedResult<T> of(@NotNull WebTextList element,
                                                             @NotNull WebTextListBlockValueExtractor<T> extractor) {
        return new WebTextListMultipleIndexedResult<>(element, emptyWebTextBlockFilter(), extractor);
    }

    @Override
    public @NotNull WebTextList getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getResults() {
        WebTextBlockFilter webTextListFilter = filterBuilder.build(element);
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationInfo = getterInvocation(GET_EXTRACTED_VALUES_METHOD, elementName)
                .addAttachmentEntry(WebFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(WebExtractorDescriptionAttachmentEntry.of(extractor));
        return runCheck(invocationInfo, () -> extractor.extractValues(webTextListFilter));
    }

    @Override
    public int getSize() {
        WebTextBlockFilter webTextListFilter = filterBuilder.build(element);
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationInfo = getterInvocation(GET_SIZE_ELEMENTS_METHOD, elementName)
                .addAttachmentEntry(WebFilterBuilderDescriptionAttachmentEntry.of(filterBuilder));
        return runCheck(invocationInfo, () -> webTextListFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public WebMultipleIndexedResult<T, WebTextList> should(WebMultipleIndexedResultMatcher<T> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull WebSingleIndexedResult<T, WebTextList> singleResult() {
        return WebTextListSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}
