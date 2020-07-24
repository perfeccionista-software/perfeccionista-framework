package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementSizeException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.texttable.WebTextTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_FILTERED_SIZE_NOT_MATCH;
import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_SIZE_METHOD;

public class WebTextTableFilterSeleniumImpl implements WebTextTableFilter {

    private final WebTextTable element;
    private final WebTextTableFilterBuilder filterBuilder;

    private String initialHash = null;
    private WebFilterResult filterResult = null;

    public WebTextTableFilterSeleniumImpl(WebTextTable element, WebTextTableFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    @Override
    public @NotNull WebFilterResult getResult() {
        if (filterResult == null) {
            executeFilter(element, filterBuilder);
        }
        return filterResult;
    }

    @Override
    public WebTextTableFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    public SingleResult<String> extractHeader(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractHeaderValues(element)
                .singleResult();
    }

    public SingleResult<String> extractOneRow(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractValues(element, filterBuilder.build(element))
                .singleResult();
    }

    @Override
    public <T> SingleResult<T> extractOneRow(WebTextTableCellValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filterBuilder.build(element))
                .singleResult();
    }

    // TODO: Implement: public Map<String, SingleResult<String>> extractOne(Set<String> columnNames)

    public MultipleResult<String> extractAllRows(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractValues(element, filterBuilder.build(element));
    }

    @Override
    public <T> MultipleResult<T> extractAllRows(WebTextTableCellValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, filterBuilder.build(element));
    }

    // TODO: Implement: public Map<String, MultipleResult<String>> extractAll(Set<String> columnNames)

    public SingleResult<String> extractFooter(String columnName) {
        return new WebTextTableCellStringExtractor(columnName)
                .extractFooterValues(element)
                .singleResult();
    }

    public WebTextTableFilter shouldHaveSize(NumberValue<Integer> expectedSize) {
        runCheck(element.getEnvironment(), InvocationName.of(SHOULD_HAVE_SIZE_METHOD, this, expectedSize), () -> {
            executeFilter(element, filterBuilder);
            int actualSize = filterResult.getIndexes().size();
            if (!expectedSize.check(actualSize)) {
                throw new ElementSizeException(ELEMENT_FILTERED_SIZE_NOT_MATCH.getMessage())
                        .setType(ExceptionType.ASSERT)
                        .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addAttachmentEntry(StringAttachmentEntry.of("Size comparison result", expectedSize.toString()));
            }
        });
        return this;
    }

    private void executeFilter(WebTextTable element, WebTextTableFilterBuilder filterBuilder) {
        Deque<WebTextTableRowConditionHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebTextTableRowConditionHolder conditionHolder : conditions) {
            WebTextTableRowCondition condition = conditionHolder.getCondition();
            WebFilterResult conditionResult = processConditions(element, condition, calculatedHash);
            calculatedHash = conditionResult.getHash();
            if (ConditionUsage.ADD == conditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
            if (ConditionUsage.SUBTRACT == conditionHolder.getUsage()) {
                indexes.removeAll(conditionResult.getIndexes());
            }
        }
        filterResult = WebFilterResult.of(indexes, calculatedHash);
    }

    private WebFilterResult processConditions(WebTextTable element, WebTextTableRowCondition condition, String hash) {
        WebFilterResult conditionResult = condition.process(element, hash);
        Deque<WebTextTableRowConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebTextTableRowConditionHolder childConditionHolder : childConditions) {
            WebTextTableRowCondition childCondition = childConditionHolder.getCondition();
            WebFilterResult childConditionResult = processConditions(element, childCondition, calculatedHash);
            calculatedHash = childConditionResult.getHash();
            if (ConditionUsage.AND == childConditionHolder.getUsage()) {
                Set<Integer> overallIndexes = new HashSet<>();
                for (int childConditionIndex : childConditionResult.getIndexes()) {
                    if (indexes.contains(childConditionIndex)) {
                        overallIndexes.add(childConditionIndex);
                    }
                }
                indexes = overallIndexes;
            }
            if (ConditionUsage.OR == childConditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
        }
        return WebFilterResult.of(indexes, calculatedHash);
    }

}
