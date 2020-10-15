package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition.WebListBlockConditionHolder;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder.WebListBlockFilterResultGroupingHolder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.blockIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.*;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterResultGrouping.*;

public class WebListFilterImpl implements WebListFilter {

    private final WebList element;
    private final WebListFilterBuilder filterBuilder;

    private String initialHash = null;
    private WebFilterResult filterResult = null;

    private WebListFilterImpl(WebList element, WebListFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static WebListFilterImpl of(@NotNull WebList element, @NotNull WebListFilterBuilder filterBuilder) {
        return new WebListFilterImpl(element, filterBuilder);
    }

    @Override
    public @NotNull <T> WebMultipleIndexedResult<T, WebList> extractAll(@NotNull WebListBlockValueExtractor<T> extractor) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractor);
    }

    @Override
    public @NotNull <T> WebSingleIndexedResult<T, WebList> extractOne(@NotNull WebListBlockValueExtractor<T> extractor) {
        return WebListMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    @Override
    public @NotNull WebList getElement() {
        return element;
    }

    @Override
    public @NotNull WebFilterResult getFilterResult() {
        if (filterResult == null) {
            executeFilter(element, filterBuilder);
        }
        return filterResult;
    }

    @Override
    public WebListFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        WebListMultipleIndexedResult<Integer> indexedResult = WebListMultipleIndexedResult.of(element, filterBuilder, blockIndex());
        matcher.check(indexedResult);
        return this;
    }

    @Override
    public WebListFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    private void executeFilter(WebList element, WebListFilterBuilder filterBuilder) {
        Deque<WebListBlockFilterResultGroupingHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebListBlockFilterResultGroupingHolder conditionHolder : conditions) {
            WebListBlockCondition condition = conditionHolder.getCondition();
            WebFilterResult conditionResult = processConditions(element, condition, calculatedHash);
            calculatedHash = conditionResult.getHash();
            if (ADD == conditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
            if (SUBTRACT == conditionHolder.getUsage()) {
                indexes.removeAll(conditionResult.getIndexes());
            }
        }
        filterResult = WebFilterResult.of(indexes, calculatedHash);
    }

    private static WebFilterResult processConditions(WebList element, WebListBlockCondition condition, String hash) {
        WebFilterResult conditionResult = condition.process(element, hash);
        Deque<WebListBlockConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebListBlockConditionHolder childConditionHolder : childConditions) {
            WebListBlockCondition childCondition = childConditionHolder.getCondition();
            WebFilterResult childConditionResult = processConditions(element, childCondition, calculatedHash);
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
                indexes.addAll(conditionResult.getIndexes());
            }
        }
        return WebFilterResult.of(indexes, calculatedHash);
    }

}
