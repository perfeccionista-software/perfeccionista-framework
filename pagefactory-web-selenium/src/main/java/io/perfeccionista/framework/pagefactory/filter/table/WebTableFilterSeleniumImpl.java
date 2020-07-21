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

// TODO: Implement: public Map<String, SingleResult<T>> extractOneRow(Map<String, WebTableCellValueExtractor<T>> columnExtractors)
// TODO: Implement: public Map<String, MultipleResult<T>> extractAllRows(Map<String, WebTableCellValueExtractor<T>> columnExtractors)
// TODO: Обернуть публичные методы в ран чек
public class WebTableFilterSeleniumImpl implements WebTableFilter {

    private final WebTable element;
    private final WebTableFilterBuilder filterBuilder;

    private String initialHash = null;
    private WebFilterResult filterResult = null;

    private WebTableFilterSeleniumImpl(WebTable element, WebTableFilterBuilder filterBuilder) {
        this.element = element;
        this.filterBuilder = filterBuilder;
    }

    public static WebTableFilterSeleniumImpl of(WebTable element, WebTableFilterBuilder filter) {
        return new WebTableFilterSeleniumImpl(element, filter);
    }

    // Не для публичного использования
    @Override
    public @NotNull WebFilterResult getResult() {
        if (filterResult == null) {
            executeFilter(element, filterBuilder);
        }
        return filterResult;
    }

    @Override
    public WebTableFilter setInitialHash(@Nullable String initialHash) {
        this.initialHash = initialHash;
        return this;
    }

    @Override
    public WebTableFilter shouldHaveSize(@NotNull NumberValue<Integer> expectedSize) {
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

    public <T> MultipleResult<T> extractAllRows(WebTableCellValueExtractor<T> extractor) {
        return extractor
                .extractValues(element, this);
    }

    public <T> SingleResult<T> extractFooter(WebTableCellValueExtractor<T> extractor) {
        return extractor
                .extractFooterValues(element)
                .singleResult();
    }

    // Если у нас Ajax таблица хэш которой постоянно меняется, то ХЗ как с ней работать...
    private void executeFilter(WebTable element, WebTableFilterBuilder filterBuilder) {
        Deque<WebTableRowConditionHolder> conditions = filterBuilder.getConditions();
        Set<Integer> indexes = new HashSet<>();
        String calculatedHash = initialHash;
        for (WebTableRowConditionHolder conditionHolder : conditions) {
            WebTableRowCondition condition = conditionHolder.getCondition();
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
