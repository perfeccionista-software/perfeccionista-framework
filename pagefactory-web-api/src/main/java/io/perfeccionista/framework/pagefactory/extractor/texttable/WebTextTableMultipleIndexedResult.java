package io.perfeccionista.framework.pagefactory.extractor.texttable;

import io.perfeccionista.framework.exceptions.attachments.WebExtractorDescriptionAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebFilterBuilderDescriptionAttachmentEntry;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.Web.emptyWebTextTableFilter;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SIZE_ELEMENTS_METHOD;

public class WebTextTableMultipleIndexedResult<T> implements WebMultipleIndexedResult<T, WebTextTable> {

    private final WebTextTable element;
    private final WebTextTableFilterBuilder filterBuilder;
    private final WebTextTableValueExtractor<T> extractor;

    private WebTextTableMultipleIndexedResult(WebTextTable element,
                                              WebTextTableFilterBuilder filterBuilder,
                                              WebTextTableValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> WebTextTableMultipleIndexedResult<T> of(@NotNull WebTextTable element,
                                                              @NotNull WebTextTableFilterBuilder filterBuilder,
                                                              @NotNull WebTextTableValueExtractor<T> extractor) {
        return new WebTextTableMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> WebTextTableMultipleIndexedResult<T> of(@NotNull WebTextTable element,
                                                              @NotNull WebTextTableValueExtractor<T> extractor) {
        return new WebTextTableMultipleIndexedResult<>(element, emptyWebTextTableFilter(), extractor);
    }

    @Override
    public @NotNull WebTextTable getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getResults() {
        var elementName = element.getElementIdentifier().getLastUsedName();
        var invocationInfo = getterInvocation(GET_EXTRACTED_VALUES_METHOD, elementName)
                .addAttachmentEntry(WebFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(WebExtractorDescriptionAttachmentEntry.of(extractor));
        return runCheck(invocationInfo, () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        WebTextTableFilter webTextTableFilter = filterBuilder.build(element);
        var elementName = element.getElementIdentifier().getLastUsedName();
        var invocationInfo = getterInvocation(GET_SIZE_ELEMENTS_METHOD, elementName)
                .addAttachmentEntry(WebFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(WebExtractorDescriptionAttachmentEntry.of(extractor));
        return runCheck(invocationInfo, () -> webTextTableFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public WebMultipleIndexedResult<T, WebTextTable> should(WebMultipleIndexedResultMatcher<T> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull WebSingleIndexedResult<T, WebTextTable> singleResult() {
        return WebTextTableSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}
