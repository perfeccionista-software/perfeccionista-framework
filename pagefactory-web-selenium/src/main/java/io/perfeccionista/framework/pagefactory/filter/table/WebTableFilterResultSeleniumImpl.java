package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementSizeException;
import io.perfeccionista.framework.exceptions.base.ExceptionType;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_FILTERED_SIZE_NOT_MATCH;
import static io.perfeccionista.framework.invocation.wrappers.CheckActionWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_SIZE_METHOD;

// TODO: Обернуть публичные методы в ран чек
public class WebTableFilterResultSeleniumImpl implements WebTableFilterResult {

    private final WebTable element;
    private final WebTableFilter filter;

    private WebConditionProcessingResult conditionProcessingResult = null;

    // TODO: Видимо сюда нужно все-таки фильтр передавать, потомучто его выполнение должно происходить в методе shouldHaveSize
    private WebTableFilterResultSeleniumImpl(WebTable element, WebTableFilter filter) {
        this.element = element;
        this.filter = filter;
    }

    public static WebTableFilterResultSeleniumImpl of(WebTable element, WebTableFilter filter) {
        return new WebTableFilterResultSeleniumImpl(element, filter);
    }

    @Override
    public String getHash() {
        // TODO: runCheck
        if (conditionProcessingResult == null) {
            executeFilter(element, filter);
        }
        return conditionProcessingResult.getHash();
    }

    @Override
    public Set<Integer> getIndexes() {
        // TODO: runCheck
        if (conditionProcessingResult == null) {
            executeFilter(element, filter);
        }
        return conditionProcessingResult.getIndexes();
    }

    // RunCheck внутри экстрактора

    public <T> SingleResult<T> extractHeader(WebTableCellValueExtractor<T> extractor) {
        return extractor
                .extractHeaderValues(element)
                .singleResult();
    }

    public <T> SingleResult<T> extractOneRow(WebTableCellValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, this)
                .singleResult();
    }

    // TODO: Implement: public Map<String, SingleResult<T>> extractOneRow(Map<String, WebTableCellValueExtractor<T>> columnExtractors)

    public <T> MultipleResult<T> extractAllRows(WebTableCellValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, this);
    }

    // TODO: Implement: public Map<String, MultipleResult<T>> extractAllRows(Map<String, WebTableCellValueExtractor<T>> columnExtractors)

    public <T> SingleResult<T> extractFooter(WebTableCellValueExtractor<T> extractor) {
        return extractor
                .extractFooterValues(element)
                .singleResult();
    }

    @Override
    public WebTableFilterResult shouldHaveSize(@NotNull NumberValue<Integer> expectedSize) {
        runCheck(element.getEnvironment(), InvocationName.of(SHOULD_HAVE_SIZE_METHOD, this, expectedSize), () -> {
            executeFilter(element, filter);
            int actualSize = conditionProcessingResult.getIndexes().size();
            if (!expectedSize.check(actualSize)) {
                throw new ElementSizeException(ELEMENT_FILTERED_SIZE_NOT_MATCH.getMessage())
                        .setType(ExceptionType.ASSERT)
                        .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()))
                        .addAttachmentEntry(StringAttachmentEntry.of("Size comparison result", expectedSize.toString()));
            }
        });
        return this;
    }

    private void executeFilter(WebTable element, WebTableFilter filter) {
        Deque<WebTableRowConditionHolder> conditions = filter.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = null;
        for (WebTableRowConditionHolder conditionHolder : conditions) {
            WebTableRowCondition condition = conditionHolder.getCondition();
            WebConditionProcessingResult conditionResult = processConditions(element, condition, calculatedHash);
            calculatedHash = conditionResult.getHash();
            if (ConditionUsage.ADD == conditionHolder.getUsage()) {
                indexes.addAll(conditionResult.getIndexes());
            }
            if (ConditionUsage.SUBTRACT == conditionHolder.getUsage()) {
                indexes.removeAll(conditionResult.getIndexes());
            }
        }
        conditionProcessingResult = WebConditionProcessingResult.of(indexes, calculatedHash);
    }

    private WebConditionProcessingResult processConditions(WebTable element, WebTableRowCondition condition, String hash) {
        WebConditionProcessingResult conditionResult = condition.process(element, hash);
        Deque<WebTableRowConditionHolder> childConditions = condition.getChildConditions();
        if (childConditions.isEmpty()) {
            return conditionResult;
        }
        String calculatedHash = conditionResult.getHash();
        Set<Integer> indexes = conditionResult.getIndexes();
        for (WebTableRowConditionHolder childConditionHolder : childConditions) {
            WebTableRowCondition childCondition = childConditionHolder.getCondition();
            WebConditionProcessingResult childConditionResult = processConditions(element, childCondition, calculatedHash);
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
        return WebConditionProcessingResult.of(indexes, hash);
    }

}
