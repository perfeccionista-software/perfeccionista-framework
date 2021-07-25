package io.perfeccionista.framework.pagefactory.filter.texttable.condition;

import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
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

public class MobileTextTableRowEmptyCondition implements MobileTextTableRowCondition {

    private final Deque<MobileTextTableRowConditionHolder> childConditions = new ArrayDeque<>();

    private boolean inverse = false;

    public MobileTextTableRowEmptyCondition allTextRows() {
        return this;
    }

    public MobileTextTableRowEmptyCondition noTextRows() {
        return this.inverse();
    }

    @Override
    public MobileTextTableRowCondition and(@NotNull MobileTextTableRowCondition condition) {
        childConditions.add(MobileTextTableRowConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public MobileTextTableRowCondition or(@NotNull MobileTextTableRowCondition condition) {
        childConditions.add(MobileTextTableRowConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public Deque<MobileTextTableRowConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull MobileTextTable element, @Nullable String hash) {

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

    private MobileTextTableRowEmptyCondition inverse() {
        this.inverse = true;
        return this;
    }

}

