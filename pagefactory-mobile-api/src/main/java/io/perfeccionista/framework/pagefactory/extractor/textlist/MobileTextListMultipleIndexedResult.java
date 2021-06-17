package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import io.perfeccionista.framework.pagefactory.filter.MobileFilters;
import io.perfeccionista.framework.pagefactory.filter.textlist.MobileTextListFilter;
import io.perfeccionista.framework.pagefactory.filter.textlist.MobileTextListFilterBuilder;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SIZE_ELEMENTS_METHOD;

public class MobileTextListMultipleIndexedResult<T> implements MobileMultipleIndexedResult<T, MobileTextList> {

    private final MobileTextList element;
    private final MobileTextListFilterBuilder filterBuilder;
    private final MobileTextListBlockValueExtractor<T> extractor;

    private MobileTextListMultipleIndexedResult(MobileTextList element,
                                             MobileTextListFilterBuilder filterBuilder,
                                             MobileTextListBlockValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> MobileTextListMultipleIndexedResult<T> of(@NotNull MobileTextList element,
                                                             @NotNull MobileTextListFilterBuilder filterBuilder,
                                                             @NotNull MobileTextListBlockValueExtractor<T> extractor) {
        return new MobileTextListMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> MobileTextListMultipleIndexedResult<T> of(@NotNull MobileTextList element,
                                                             @NotNull MobileTextListBlockValueExtractor<T> extractor) {
        return new MobileTextListMultipleIndexedResult<>(element, MobileFilters.emptyMobileTextListFilter(), extractor);
    }

    @Override
    public @NotNull MobileTextList getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getResults() {
        return runCheck(getterInvocation(GET_EXTRACTED_VALUES_METHOD, element, filterBuilder, extractor),
                () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        MobileTextListFilter webTextListFilter = filterBuilder.build(element);
        return runCheck(getterInvocation(GET_SIZE_ELEMENTS_METHOD, element, filterBuilder),
                () -> webTextListFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public MobileMultipleIndexedResult<T, MobileTextList> should(MobileMultipleIndexedResultMatcher<T> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull MobileSingleIndexedResult<T, MobileTextList> singleResult() {
        return MobileTextListSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}
