package io.perfeccionista.framework.pagefactory.extractor.texttable;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.filter.texttable.MobileTextTableFilter;
import io.perfeccionista.framework.pagefactory.filter.texttable.MobileTextTableFilterBuilder;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_EXTRACTED_VALUES_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_SIZE_ELEMENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.filter.MobileFilters.emptyMobileTextTableFilter;

public class MobileTextTableMultipleIndexedResult<T> implements MobileMultipleIndexedResult<T, MobileTextTable> {

    private final MobileTextTable element;
    private final MobileTextTableFilterBuilder filterBuilder;
    private final MobileTextTableValueExtractor<T> extractor;

    private MobileTextTableMultipleIndexedResult(MobileTextTable element,
                                              MobileTextTableFilterBuilder filterBuilder,
                                              MobileTextTableValueExtractor<T> extractor) {
        this.element = element;
        this.filterBuilder = filterBuilder;
        this.extractor = extractor;
    }

    public static <T> MobileTextTableMultipleIndexedResult<T> of(@NotNull MobileTextTable element,
                                                              @NotNull MobileTextTableFilterBuilder filterBuilder,
                                                              @NotNull MobileTextTableValueExtractor<T> extractor) {
        return new MobileTextTableMultipleIndexedResult<>(element, filterBuilder, extractor);
    }

    public static <T> MobileTextTableMultipleIndexedResult<T> of(@NotNull MobileTextTable element,
                                                              @NotNull MobileTextTableValueExtractor<T> extractor) {
        return new MobileTextTableMultipleIndexedResult<>(element, emptyMobileTextTableFilter(), extractor);
    }

    @Override
    public @NotNull MobileTextTable getElement() {
        return element;
    }

    @Override
    public Map<Integer, T> getResults() {
        return runCheck(getterInvocation(GET_EXTRACTED_VALUES_METHOD, element, filterBuilder, extractor),
                () -> extractor.extractValues(filterBuilder.build(element)));
    }

    @Override
    public int getSize() {
        MobileTextTableFilter webTextTableFilter = filterBuilder.build(element);
        return runCheck(getterInvocation(GET_SIZE_ELEMENTS_METHOD, element, filterBuilder),
                () -> webTextTableFilter.getFilterResult().getIndexes().size());
    }

    @Override
    public MobileMultipleIndexedResult<T, MobileTextTable> should(MobileMultipleIndexedResultMatcher<T> matcher) {
        matcher.check(this);
        return this;
    }

    @Override
    public @NotNull MobileSingleIndexedResult<T, MobileTextTable> singleResult() {
        return MobileTextTableSingleIndexedResult.of(element, filterBuilder, extractor);
    }

}

