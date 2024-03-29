package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.exceptions.attachments.MobileExtractorDescriptionAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.MobileFilterBuilderDescriptionAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.filter.MobileFilters;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilter;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SIZE_ELEMENTS_METHOD;

public class MobileListMultipleIndexedResult<T> implements MobileMultipleIndexedResult<T, MobileList> {

    private final MobileList element;
    private final MobileListFilterBuilder filterBuilder;
    private final MobileListBlockValueExtractor<T> extractor;

    private MobileListMultipleIndexedResult(MobileList element,
                                         MobileListFilterBuilder filterBuilder,
                                         MobileListBlockValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> MobileListMultipleIndexedResult<T> of(@NotNull MobileList element,
                                                         @NotNull MobileListFilterBuilder filterBuilder,
                                                         @NotNull MobileListBlockValueExtractor<T> extractor) {
        return new MobileListMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> MobileListMultipleIndexedResult<T> of(@NotNull MobileList element,
                                                         @NotNull MobileListBlockValueExtractor<T> extractor) {
        return new MobileListMultipleIndexedResult<>(element, MobileFilters.emptyMobileListFilter(), extractor);
    }

    @Override
    public @NotNull MobileList getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getResults() {
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationInfo = getterInvocation(GET_EXTRACTED_VALUES_METHOD, elementName)
                .addAttachmentEntry(MobileFilterBuilderDescriptionAttachmentEntry.of(filterBuilder))
                .addAttachmentEntry(MobileExtractorDescriptionAttachmentEntry.of(extractor));
        return MultipleAttemptInvocationWrapper.repeatInvocation(invocationInfo, () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        MobileListFilter webListFilter = filterBuilder.build(element);
        String elementName = element.getElementIdentifier().getLastUsedName();
        InvocationInfo invocationInfo = getterInvocation(GET_SIZE_ELEMENTS_METHOD, elementName)
                .addAttachmentEntry(MobileFilterBuilderDescriptionAttachmentEntry.of(filterBuilder));
        return MultipleAttemptInvocationWrapper.repeatInvocation(invocationInfo, () -> webListFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public MobileMultipleIndexedResult<T, MobileList> should(MobileMultipleIndexedResultMatcher<T> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull MobileSingleIndexedResult<T, MobileList> singleResult() {
        return MobileListSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}
