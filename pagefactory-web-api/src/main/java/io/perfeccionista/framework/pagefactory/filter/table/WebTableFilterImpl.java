package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder.WebTableRowFilterResultGroupingHolder;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition.WebTableRowConditionHolder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.rowIndex;
import static io.perfeccionista.framework.pagefactory.filter.WebFilterResultGrouping.*;

// TODO: Implement: public Map<String, SingleResult<T>> extractOneRow(Map<String, WebTableCellValueExtractor<T>> columnExtractors)
// TODO: Implement: public Map<String, MultipleResult<T>> extractAllRows(Map<String, WebTableCellValueExtractor<T>> columnExtractors)
// TODO: Обернуть публичные методы в ран чек
public class WebTableFilterImpl implements WebTableFilter {

    private final WebTable element;
    private final WebTableFilterBuilder filterBuilder;

    private String initialHash = null;
    private WebFilterResult filterResult = null;

    private WebTableFilterImpl(WebTable element, WebTableFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static WebTableFilterImpl of(@NotNull WebTable element, @NotNull WebTableFilterBuilder filter) {
        return new WebTableFilterImpl(element, filter);
    }

    @Override
    public @NotNull <T> WebSingleIndexedResult<T, WebTable> extractHeader(@NotNull WebTableCellValueExtractor<T> extractor) {
        return WebTableMultipleIndexedResult.of(element, filterBuilder, extractor.fromHeader())
                .singleResult();
    }

    @Override
    public @NotNull <T> WebSingleIndexedResult<T, WebTable> extractOneRow(@NotNull WebTableCellValueExtractor<T> extractor) {
        return WebTableMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    @Override
    public @NotNull <T> WebMultipleIndexedResult<T, WebTable> extractAllRows(@NotNull WebTableCellValueExtractor<T> extractor) {
        return WebTableMultipleIndexedResult.of(element, filterBuilder, extractor);
    }

    @Override
    public @NotNull <T> WebSingleIndexedResult<T, WebTable> extractFooter(@NotNull WebTableCellValueExtractor<T> extractor) {
        return WebTableMultipleIndexedResult.of(element, filterBuilder, extractor.fromFooter())
                .singleResult();
    }

    @Override
    public @NotNull WebTable getElement() {
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
    public WebTableFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        WebTableMultipleIndexedResult<Integer> indexedResult = WebTableMultipleIndexedResult.of(element, filterBuilder, rowIndex());
        matcher.check(indexedResult);
        return this;
    }

    @Override
    public WebTableFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    // Если у нас Ajax таблица хэш которой постоянно меняется, то ХЗ как с ней работать...
    private void executeFilter(WebTable element, WebTableFilterBuilder filterBuilder) {
        Deque<WebTableRowFilterResultGroupingHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebTableRowFilterResultGroupingHolder conditionHolder : conditions) {
            WebTableRowCondition condition = conditionHolder.getCondition();
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

    private WebFilterResult processConditions(WebTable element, WebTableRowCondition condition, String hash) {
        WebFilterResult conditionResult = condition.process(element, hash);
        Deque<WebTableRowConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebTableRowConditionHolder childConditionHolder : childConditions) {
            WebTableRowCondition childCondition = childConditionHolder.getCondition();
            WebFilterResult childConditionResult = processConditions(element, childCondition, calculatedHash);
            calculatedHash = childConditionResult.getHash();
            if (WebConditionGrouping.AND == childConditionHolder.getUsage()) {
                Set<Integer> overallIndexes = new HashSet<>();
                for (int childConditionIndex : childConditionResult.getIndexes()) {
                    if (indexes.contains(childConditionIndex)) {
                        overallIndexes.add(childConditionIndex);
                    }
                }
                indexes = overallIndexes;
            }
            if (WebConditionGrouping.OR == childConditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
        }
        return WebFilterResult.of(indexes, calculatedHash);
    }

}
