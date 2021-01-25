package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.extractor.table.MobileTableMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.extractor.table.MobileTableValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilterBuilder.MobileTableRowFilterResultGroupingHolder;
import io.perfeccionista.framework.pagefactory.filter.table.condition.MobileTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.MobileTableRowCondition.MobileTableRowConditionHolder;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.extractor.MobileExtractors.rowIndex;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.ADD;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.SUBTRACT;

public class MobileTableFilterImpl implements MobileTableFilter {

    private final MobileTable element;
    private final MobileTableFilterBuilder filterBuilder;

    private String initialHash = null;
    private FilterResult filterResult = null;

    private MobileTableFilterImpl(MobileTable element, MobileTableFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static MobileTableFilterImpl of(@NotNull MobileTable element, @NotNull MobileTableFilterBuilder filter) {
        return new MobileTableFilterImpl(element, filter);
    }

    @Override
    public @NotNull <T> MobileSingleIndexedResult<T, MobileTable> extractHeader(@NotNull MobileTableValueExtractor<T> extractor) {
        return MobileTableMultipleIndexedResult.of(element, filterBuilder, extractor.fromHeader())
                .singleResult();
    }

    @Override
    public @NotNull <T> MobileSingleIndexedResult<T, MobileTable> extractRow(@NotNull MobileTableValueExtractor<T> extractor) {
        return MobileTableMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    @Override
    public @NotNull <T> MobileMultipleIndexedResult<T, MobileTable> extractRows(@NotNull MobileTableValueExtractor<T> extractor) {
        return MobileTableMultipleIndexedResult.of(element, filterBuilder, extractor);
    }

    @Override
    public @NotNull <T> MobileSingleIndexedResult<T, MobileTable> extractFooter(@NotNull MobileTableValueExtractor<T> extractor) {
        return MobileTableMultipleIndexedResult.of(element, filterBuilder, extractor.fromFooter())
                .singleResult();
    }

    @Override
    public @NotNull MobileTable getElement() {
        return element;
    }

    @Override
    public @NotNull FilterResult getFilterResult() {
        if (filterResult == null) {
            executeFilter(element, filterBuilder);
        }
        return filterResult;
    }

    @Override
    public MobileTableFilter should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher) {
        MobileTableMultipleIndexedResult<Integer> indexedResult = MobileTableMultipleIndexedResult.of(element, filterBuilder, rowIndex());
        matcher.check(indexedResult);
        return this;
    }

    @Override
    public MobileTableFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    // Если у нас Ajax таблица хэш которой постоянно меняется, то ХЗ как с ней работать...
    private void executeFilter(MobileTable element, MobileTableFilterBuilder filterBuilder) {
        Deque<MobileTableRowFilterResultGroupingHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (MobileTableRowFilterResultGroupingHolder conditionHolder : conditions) {
            MobileTableRowCondition condition = conditionHolder.getCondition();
            FilterResult conditionResult = processConditions(element, condition, calculatedHash);
            calculatedHash = conditionResult.getHash();
            if (ADD == conditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
            if (SUBTRACT == conditionHolder.getUsage()) {
                indexes.removeAll(conditionResult.getIndexes());
            }
        }
        filterResult = FilterResult.of(indexes, calculatedHash);
    }

    private FilterResult processConditions(MobileTable element, MobileTableRowCondition condition, String hash) {
        FilterResult conditionResult = condition.process(element, hash);
        Deque<MobileTableRowConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (MobileTableRowConditionHolder childConditionHolder : childConditions) {
            MobileTableRowCondition childCondition = childConditionHolder.getCondition();
            FilterResult childConditionResult = processConditions(element, childCondition, calculatedHash);
            calculatedHash = childConditionResult.getHash();
            if (ConditionGrouping.AND == childConditionHolder.getUsage()) {
                Set<Integer> overallIndexes = new HashSet<>();
                for (int childConditionIndex : childConditionResult.getIndexes()) {
                    if (indexes.contains(childConditionIndex)) {
                        overallIndexes.add(childConditionIndex);
                    }
                }
                indexes = overallIndexes;
            }
            if (ConditionGrouping.OR == childConditionHolder.getUsage()) {
                indexes.addAll(childConditionResult.getIndexes());
            }
        }
        return FilterResult.of(indexes, calculatedHash);
    }

}
