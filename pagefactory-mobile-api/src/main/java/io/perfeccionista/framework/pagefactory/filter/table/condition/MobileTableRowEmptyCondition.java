package io.perfeccionista.framework.pagefactory.filter.table.condition;

import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsPresentOperationType;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

public class MobileTableRowEmptyCondition implements MobileTableRowCondition {

    private final Deque<MobileTableRowConditionHolder> childConditions = new ArrayDeque<>();

    private boolean inverse = false;

    public MobileTableRowEmptyCondition() {
    }

    public MobileTableRowEmptyCondition allRows() {
        return this;
    }

    public MobileTableRowEmptyCondition noRows() {
        return this.inverse();
    }

    @Override
    public MobileTableRowCondition and(@NotNull MobileTableRowCondition condition) {
        childConditions.add(MobileTableRowConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public MobileTableRowCondition or(@NotNull MobileTableRowCondition condition) {
        childConditions.add(MobileTableRowConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public Deque<MobileTableRowConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull MobileTable element, @Nullable String hash) {

        // Цепочка от корня страницы до MobileTable column body
        MobileLocatorChain tableLocatorChain = element.getLocatorChain();
        MobileLocatorHolder tableLocatorHolder = tableLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        tableLocatorChain.addLastLocator(element.getRequiredLocator(ITEM));

        // Формируем и выполняем операцию
        MobileElementOperation<Boolean> operation = MobileElementOperation.of(tableLocatorChain, MobileGetIsPresentOperationType.of(element));
        MobileElementOperationResult<Boolean> operationResult = element.getMobileDeviceDispatcher().executor()
                .executeMobileElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(MobileElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(tableLocatorHolder.getLocatorId());
        Set<Integer> indexes = operationResult.getResults().keySet();

        // Формируем ответ
        if (inverse) {
            return FilterResult.of(new HashSet<>(), calculatedHash);
        }
        return FilterResult.of(indexes, calculatedHash);
    }

    private MobileTableRowEmptyCondition inverse() {
        inverse = true;
        return this;
    }

}
