package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.extractor.list.MobileListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.MobileListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder.MobileListBlockFilterResultGroupingHolder;
import io.perfeccionista.framework.pagefactory.filter.list.condition.MobileListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.MobileListBlockCondition.MobileListBlockConditionHolder;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.extractor.MobileExtractors.blockIndex;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.ADD;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.SUBTRACT;

public class MobileListFilterImpl implements MobileListFilter {

    private final MobileList element;
    private final MobileListFilterBuilder filterBuilder;

    private String initialHash = null;
    private FilterResult filterResult = null;

    private MobileListFilterImpl(MobileList element, MobileListFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static MobileListFilterImpl of(@NotNull MobileList element, @NotNull MobileListFilterBuilder filterBuilder) {
        return new MobileListFilterImpl(element, filterBuilder);
    }

    @Override
    public @NotNull <T> MobileMultipleIndexedResult<T, MobileList> extractAll(@NotNull MobileListBlockValueExtractor<T> extractor) {
        return MobileListMultipleIndexedResult.of(element, filterBuilder, extractor);
    }

    @Override
    public @NotNull <T> MobileSingleIndexedResult<T, MobileList> extractOne(@NotNull MobileListBlockValueExtractor<T> extractor) {
        return MobileListMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    @Override
    public @NotNull MobileList getElement() {
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
    public MobileListFilter should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher) {
        MobileListMultipleIndexedResult<Integer> indexedResult = MobileListMultipleIndexedResult.of(element, filterBuilder, blockIndex());
        matcher.check(indexedResult);
        return this;
    }

    @Override
    public MobileListFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    private void executeFilter(MobileList element, MobileListFilterBuilder filterBuilder) {
        Deque<MobileListBlockFilterResultGroupingHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (MobileListBlockFilterResultGroupingHolder conditionHolder : conditions) {
            MobileListBlockCondition condition = conditionHolder.getCondition();
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

    private static FilterResult processConditions(MobileList element, MobileListBlockCondition condition, String hash) {
        FilterResult conditionResult = condition.process(element, hash);
        Deque<MobileListBlockConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (MobileListBlockConditionHolder childConditionHolder : childConditions) {
            MobileListBlockCondition childCondition = childConditionHolder.getCondition();
            FilterResult childConditionResult = processConditions(element, childCondition, calculatedHash);
            calculatedHash = childConditionResult.getHash();
            if (AND == childConditionHolder.getUsage()) {
                Set<Integer> overallIndexes = new HashSet<>();
                for (int childConditionIndex : childConditionResult.getIndexes()) {
                    if (indexes.contains(childConditionIndex)) {
                        overallIndexes.add(childConditionIndex);
                    }
                }
                indexes = overallIndexes;
            }
            if (OR == childConditionHolder.getUsage()) {
                indexes.addAll(childConditionResult.getIndexes());
            }
        }
        return FilterResult.of(indexes, calculatedHash);
    }

}
