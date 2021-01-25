package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.matcher.result.MobileMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import io.perfeccionista.framework.pagefactory.extractor.textlist.MobileTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.textlist.MobileTextListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.textlist.MobileTextListFilterBuilder.MobileTextListBlockFilterResultGroupingHolder;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.MobileTextListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.MobileTextListBlockCondition.MobileTextListBlockConditionHolder;
import io.perfeccionista.framework.result.MobileMultipleIndexedResult;
import io.perfeccionista.framework.result.MobileSingleIndexedResult;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.extractor.MobileExtractors.textBlockIndex;
import static io.perfeccionista.framework.pagefactory.extractor.MobileExtractors.textBlockValue;
import static org.apiguardian.api.API.Status.INTERNAL;
import static org.apiguardian.api.API.Status.STABLE;

public class MobileTextListFilterImpl implements MobileTextListFilter {

    private final MobileTextList element;
    private final MobileTextListFilterBuilder filterBuilder;

    private String initialHash = null;
    private FilterResult filterResult = null;

    private MobileTextListFilterImpl(MobileTextList element, MobileTextListFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static MobileTextListFilterImpl of(@NotNull MobileTextList element, @NotNull MobileTextListFilterBuilder filterBuilder) {
        return new MobileTextListFilterImpl(element, filterBuilder);
    }

    @API(status = STABLE)
    public @NotNull MobileSingleIndexedResult<String, MobileTextList> extractOne() {
        return MobileTextListMultipleIndexedResult.of(element, filterBuilder, textBlockValue())
                .singleResult();
    }

    @API(status = STABLE)
    public @NotNull MobileMultipleIndexedResult<String, MobileTextList> extractAll() {
        return MobileTextListMultipleIndexedResult.of(element, filterBuilder, textBlockValue());
    }

    @API(status = INTERNAL)
    public @NotNull <T> MobileSingleIndexedResult<T, MobileTextList> extractOne(@NotNull MobileTextListBlockValueExtractor<T> extractor) {
        return MobileTextListMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    @API(status = INTERNAL)
    public @NotNull <T> MobileMultipleIndexedResult<T, MobileTextList> extractAll(@NotNull MobileTextListBlockValueExtractor<T> extractor) {
        return MobileTextListMultipleIndexedResult.of(element, filterBuilder, extractor);
    }

    @Override
    public @NotNull MobileTextList getElement() {
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
    public MobileTextListFilter should(@NotNull MobileMultipleIndexedResultMatcher<Integer> matcher) {
        MobileTextListMultipleIndexedResult<Integer> indexedResult = MobileTextListMultipleIndexedResult.of(element, filterBuilder, textBlockIndex());
        matcher.check(indexedResult);
        return this;
    }

    @Override
    public MobileTextListFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    private void executeFilter(MobileTextList element, MobileTextListFilterBuilder filterBuilder) {
        Deque<MobileTextListBlockFilterResultGroupingHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (MobileTextListBlockFilterResultGroupingHolder conditionHolder : conditions) {
            MobileTextListBlockCondition condition = conditionHolder.getCondition();
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

    private FilterResult processConditions(MobileTextList element, MobileTextListBlockCondition condition, String hash) {
        FilterResult conditionResult = condition.process(element, hash);
        Deque<MobileTextListBlockConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (MobileTextListBlockConditionHolder childConditionHolder : childConditions) {
            MobileTextListBlockCondition childCondition = childConditionHolder.getCondition();
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
