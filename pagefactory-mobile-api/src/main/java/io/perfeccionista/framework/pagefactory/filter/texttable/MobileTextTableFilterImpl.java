package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.extractor.texttable.MobileTextTableMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.extractor.texttable.MobileTextTableValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.texttable.MobileTextTableFilterBuilder.MobileTextTableRowFilterResultGroupingHolder;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.MobileTextTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.MobileTextTableRowCondition.MobileTextTableRowConditionHolder;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.extractor.MobileExtractors.textCellValue;
import static io.perfeccionista.framework.pagefactory.extractor.MobileExtractors.textRowIndex;

public class MobileTextTableFilterImpl implements MobileTextTableFilter {

    private final MobileTextTable element;
    private final MobileTextTableFilterBuilder filterBuilder;

    private String initialHash = null;
    private FilterResult filterResult = null;

    private MobileTextTableFilterImpl(MobileTextTable element, MobileTextTableFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static MobileTextTableFilterImpl of(@NotNull MobileTextTable element, @NotNull MobileTextTableFilterBuilder filterBuilder) {
        return new MobileTextTableFilterImpl(element, filterBuilder);
    }

    public @NotNull MobileSingleIndexedResult<String, MobileTextTable> extractHeader(@NotNull String columnName) {
        return MobileTextTableMultipleIndexedResult.of(element, filterBuilder, textCellValue(columnName).fromHeader())
                .singleResult();
    }

    public @NotNull MobileSingleIndexedResult<String, MobileTextTable> extractRow(@NotNull String columnName) {
        return MobileTextTableMultipleIndexedResult.of(element, filterBuilder, textCellValue(columnName))
                .singleResult();
    }

    @Override
    public @NotNull <T> MobileSingleIndexedResult<T, MobileTextTable> extractRow(@NotNull MobileTextTableValueExtractor<T> extractor) {
        return MobileTextTableMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    public @NotNull MobileMultipleIndexedResult<String, MobileTextTable> extractRows(@NotNull String columnName) {
        return MobileTextTableMultipleIndexedResult.of(element, filterBuilder, textCellValue(columnName));
    }

    @Override
    public @NotNull <T> MobileMultipleIndexedResult<T, MobileTextTable> extractRows(@NotNull MobileTextTableValueExtractor<T> extractor) {
        return MobileTextTableMultipleIndexedResult.of(element, filterBuilder, extractor);
    }


    public @NotNull MobileSingleIndexedResult<String, MobileTextTable> extractFooter(@NotNull String columnName) {
        return MobileTextTableMultipleIndexedResult.of(element, filterBuilder, textCellValue(columnName).fromFooter())
                .singleResult();
    }

    @Override
    public @NotNull MobileTextTable getElement() {
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
    public MobileTextTableFilter should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher) {
        MobileTextTableMultipleIndexedResult<Integer> indexedResult = MobileTextTableMultipleIndexedResult.of(element, filterBuilder, textRowIndex());
        matcher.check(indexedResult);
        return this;
    }

    @Override
    public MobileTextTableFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    private void executeFilter(MobileTextTable element, MobileTextTableFilterBuilder filterBuilder) {
        Deque<MobileTextTableRowFilterResultGroupingHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (MobileTextTableRowFilterResultGroupingHolder conditionHolder : conditions) {
            MobileTextTableRowCondition condition = conditionHolder.getCondition();
            FilterResult conditionResult = processConditions(element, condition, calculatedHash);
            calculatedHash = conditionResult.getHash();
            if (FilterResultGrouping.ADD == conditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
            if (FilterResultGrouping.SUBTRACT == conditionHolder.getUsage()) {
                indexes.removeAll(conditionResult.getIndexes());
            }
        }
        filterResult = FilterResult.of(indexes, calculatedHash);
    }

    private FilterResult processConditions(MobileTextTable element, MobileTextTableRowCondition condition, String hash) {
        FilterResult conditionResult = condition.process(element, hash);
        Deque<MobileTextTableRowConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (MobileTextTableRowConditionHolder childConditionHolder : childConditions) {
            MobileTextTableRowCondition childCondition = childConditionHolder.getCondition();
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
