package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.exceptions.attachments.MobileExtractorDescriptionAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.MobileFilterBuilderDescriptionAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.filter.MobileFilters;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilter;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilterBuilder;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SIZE_ELEMENTS_METHOD;

public class MobileTableMultipleIndexedResult<T> implements MobileMultipleIndexedResult<T, MobileTable> {

    private final MobileTable element;
    private final MobileTableFilterBuilder filterBuilder;
    private final MobileTableValueExtractor<T> extractor;

    private MobileTableMultipleIndexedResult(MobileTable element,
                                          MobileTableFilterBuilder filterBuilder,
                                          MobileTableValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> MobileTableMultipleIndexedResult<T> of(@NotNull MobileTable element,
                                                          @NotNull MobileTableFilterBuilder filterBuilder,
                                                          @NotNull MobileTableValueExtractor<T> extractor) {
        return new MobileTableMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> MobileTableMultipleIndexedResult<T> of(@NotNull MobileTable element,
                                                          @NotNull MobileTableValueExtractor<T> extractor) {
        return new MobileTableMultipleIndexedResult<>(element, MobileFilters.emptyMobileTableFilter(), extractor);
    }

    @Override
    public @NotNull MobileTable getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getResults() {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationInfo = getterInvocation(GET_EXTRACTED_VALUES_METHOD, elementName)
                .addAttachmentEntry(MobileFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(MobileExtractorDescriptionAttachmentEntry.of(extractor));
        return repeatInvocation(invocationInfo, () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        MobileTableFilter webTableFilter = filterBuilder.build(element);
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationInfo = getterInvocation(GET_SIZE_ELEMENTS_METHOD, elementName)
                .addAttachmentEntry(MobileFilterBuilderDescriptionAttachmentEntry.of(filterBuilder));
        return repeatInvocation(invocationInfo, () -> webTableFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public MobileMultipleIndexedResult<T, MobileTable> should(MobileMultipleIndexedResultMatcher<T> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull MobileSingleIndexedResult<T, MobileTable> singleResult() {
        return MobileTableSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}
