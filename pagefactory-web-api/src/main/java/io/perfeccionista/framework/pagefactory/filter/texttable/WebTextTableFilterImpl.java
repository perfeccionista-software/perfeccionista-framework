package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.invocation.runner.InvocationInfo;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableMultipleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder.WebTextTableRowFilterResultGroupingHolder;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition.WebTextTableRowConditionHolder;
import io.perfeccionista.framework.result.WebMultipleIndexedResult;
import io.perfeccionista.framework.result.WebSingleIndexedResult;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.Web.textCellValue;
import static io.perfeccionista.framework.Web.textRowIndex;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;


// TODO: Implement: public Map<String, SingleResult<String>> extractOne(Set<String> columnNames)
// TODO: Implement: public Map<String, MultipleResult<String>> extractAll(Set<String> columnNames)
public class WebTextTableFilterImpl implements WebTextTableFilter {

    private final WebTextTable element;
    private final WebTextTableFilterBuilder filterBuilder;

    private String initialHash = null;
    private FilterResult filterResult = null;

    private WebTextTableFilterImpl(WebTextTable element, WebTextTableFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static WebTextTableFilterImpl of(@NotNull WebTextTable element, @NotNull WebTextTableFilterBuilder filterBuilder) {
        return new WebTextTableFilterImpl(element, filterBuilder);
    }

    public @NotNull WebSingleIndexedResult<String, WebTextTable> extractHeader(@NotNull String columnName) {
        return WebTextTableMultipleIndexedResult.of(element, filterBuilder, textCellValue(columnName).fromHeader())
                .singleResult();
    }

    public @NotNull WebSingleIndexedResult<String, WebTextTable> extractRow(@NotNull String columnName) {
        return WebTextTableMultipleIndexedResult.of(element, filterBuilder, textCellValue(columnName))
                .singleResult();
    }

    @Override
    public @NotNull <T> WebSingleIndexedResult<T, WebTextTable> extractRow(@NotNull WebTextTableValueExtractor<T> extractor) {
        return WebTextTableMultipleIndexedResult.of(element, filterBuilder, extractor)
                .singleResult();
    }

    public @NotNull WebMultipleIndexedResult<String, WebTextTable> extractRows(@NotNull String columnName) {
        return WebTextTableMultipleIndexedResult.of(element, filterBuilder, textCellValue(columnName));
    }

    @Override
    public @NotNull <T> WebMultipleIndexedResult<T, WebTextTable> extractRows(@NotNull WebTextTableValueExtractor<T> extractor) {
        return WebTextTableMultipleIndexedResult.of(element, filterBuilder, extractor);
    }


    public @NotNull WebSingleIndexedResult<String, WebTextTable> extractFooter(@NotNull String columnName) {
        return WebTextTableMultipleIndexedResult.of(element, filterBuilder, textCellValue(columnName).fromFooter())
                .singleResult();
    }

    @Override
    public @NotNull WebTextTable getElement() {
        return element;
    }

    @Override
    public @NotNull FilterResult getFilterResult() {
        executeFilter(element, filterBuilder);
        return filterResult;
    }

    @Override
    public WebTextTableFilter should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher) {
        WebTextTableMultipleIndexedResult<Integer> indexedResult = WebTextTableMultipleIndexedResult.of(element, filterBuilder, textRowIndex());
        matcher.check(indexedResult);
        return this;
    }

    @Override
    public int size() {
        return runCheck(InvocationInfo.getterInvocation(""), () -> {
            executeFilter(element, filterBuilder);
            return filterResult.getSize();
        });
    }

    @Override
    public WebTextTableFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    private void executeFilter(WebTextTable element, WebTextTableFilterBuilder filterBuilder) {
        Deque<WebTextTableRowFilterResultGroupingHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebTextTableRowFilterResultGroupingHolder conditionHolder : conditions) {
            WebTextTableRowCondition condition = conditionHolder.getCondition();
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

    private FilterResult processConditions(WebTextTable element, WebTextTableRowCondition condition, String hash) {
        FilterResult conditionResult = condition.process(element, hash);
        Deque<WebTextTableRowConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebTextTableRowConditionHolder childConditionHolder : childConditions) {
            WebTextTableRowCondition childCondition = childConditionHolder.getCondition();
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
