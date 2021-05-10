package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioButtonValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.radio.WebRadioGroupMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition.WebRadioButtonConditionHolder;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilterBuilder.WebRadioButtonFilterResultGroupingHolder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.Web.index;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.ADD;
import static io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping.SUBTRACT;

public class WebRadioGroupFilterImpl implements WebRadioGroupFilter {

    private final WebRadioGroup element;
    private final WebRadioGroupFilterBuilder filterBuilder;

    private String initialHash = null;
    private FilterResult filterResult = null;

    private WebRadioGroupFilterImpl(WebRadioGroup element, WebRadioGroupFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static WebRadioGroupFilterImpl of(@NotNull WebRadioGroup element, @NotNull WebRadioGroupFilterBuilder filterBuilder) {
        return new WebRadioGroupFilterImpl(element, filterBuilder);
    }

    @Override
    public @NotNull <T> WebSingleIndexedResult<T, WebRadioGroup> extractOne(@NotNull WebRadioButtonValueExtractor<T> extractor) {
        return WebRadioGroupMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    @Override
    public @NotNull <T> WebMultipleIndexedResult<T, WebRadioGroup> extractAll(@NotNull WebRadioButtonValueExtractor<T> extractor) {
        return WebRadioGroupMultipleIndexedResult.of(element, filterBuilder, extractor);
    }

    @Override
    public @NotNull WebRadioGroup getElement() {
        return element;
    }

    @Override
    public @NotNull FilterResult getFilterResult() {
        executeFilter(element, filterBuilder);
        return filterResult;
    }

    @Override
    public WebRadioGroupFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        WebRadioGroupMultipleIndexedResult<Integer> indexedResult = WebRadioGroupMultipleIndexedResult.of(element, filterBuilder, index());
        matcher.check(indexedResult);
        return this;
    }

    @Override
    public WebRadioGroupFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    private void executeFilter(WebRadioGroup element, WebRadioGroupFilterBuilder filterBuilder) {
        Deque<WebRadioButtonFilterResultGroupingHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebRadioButtonFilterResultGroupingHolder conditionHolder : conditions) {
            WebRadioButtonCondition condition = conditionHolder.getCondition();
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

    private static FilterResult processConditions(WebRadioGroup element, WebRadioButtonCondition condition, String hash) {
        FilterResult conditionResult = condition.process(element, hash);
        Deque<WebRadioButtonConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebRadioButtonConditionHolder childConditionHolder : childConditions) {
            WebRadioButtonCondition childCondition = childConditionHolder.getCondition();
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
