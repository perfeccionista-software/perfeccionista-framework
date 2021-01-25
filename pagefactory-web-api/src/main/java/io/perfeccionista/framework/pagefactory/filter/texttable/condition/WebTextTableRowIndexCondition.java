package io.perfeccionista.framework.pagefactory.filter.texttable.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsPresentOperationType;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

public class WebTextTableRowIndexCondition implements WebTextTableRowCondition {

    private final Deque<WebTextTableRowConditionHolder> childConditions = new ArrayDeque<>();

    private final Integer expectedIndex;
    private final NumberValue<Integer> expectedValue;

    private boolean inverse = false;

    public WebTextTableRowIndexCondition(@NotNull Integer expectedIndex) {
        this.expectedIndex = expectedIndex;
        this.expectedValue = null;
    }

    public WebTextTableRowIndexCondition(@NotNull NumberValue<Integer> expectedValue) {
        this.expectedIndex = null;
        this.expectedValue = expectedValue;
    }

    public WebTextTableRowIndexCondition withTextRowIndex() {
        return this;
    }

    public WebTextTableRowIndexCondition withoutTextRowIndex() {
        return this.inverse();
    }

    @Override
    public WebTextTableRowCondition and(@NotNull WebTextTableRowCondition condition) {
        childConditions.add(WebTextTableRowConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public WebTextTableRowCondition or(@NotNull WebTextTableRowCondition condition) {
        childConditions.add(WebTextTableRowConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public Deque<WebTextTableRowConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull WebTextTable element, @Nullable String hash) {

        // Цепочка от корня страницы до WebTable column body
        WebLocatorChain tableLocatorChain = element.getLocatorChain();
        WebLocatorHolder tableLocatorHolder = tableLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        tableLocatorChain.addLastLocator(element.getRequiredLocator(TBODY_ROW));

        // Формируем и выполняем операцию
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(element);
        WebElementOperation<Boolean> operation = WebElementOperation.of(tableLocatorChain, operationType);
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(tableLocatorHolder.getLocatorId());
        Set<Integer> indexes = operationResult.getResults().keySet();
        Set<Integer> matches = getMatches(indexes);

        // Формируем ответ
        return FilterResult.of(matches, calculatedHash);
    }

    private Set<Integer> getMatches(Set<Integer> indexes) {
        Set<Integer> matches = new HashSet<>();
        indexes.forEach(index -> {
            boolean check;
            if (expectedIndex != null) {
                check = expectedIndex.equals(index);
            } else {
                check = expectedValue.check(index);
            }
            if ((check && !inverse) || (!check && inverse)) {
                matches.add(index);
            }
        });
        return matches;
    }

    private WebTextTableRowIndexCondition inverse() {
        inverse = true;
        return this;
    }

}
