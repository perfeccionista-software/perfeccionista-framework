package io.perfeccionista.framework.pagefactory.filter.textlist.condition;

import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
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

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LI;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

public class MobileTextListBlockEmptyCondition implements MobileTextListBlockCondition {

    private final Deque<MobileTextListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private boolean inverse = false;

    public MobileTextListBlockEmptyCondition allTextBlocks() {
        return this;
    }

    public MobileTextListBlockEmptyCondition noTextBlocks() {
        return this.inverse();
    }

    @Override
    public MobileTextListBlockEmptyCondition and(@NotNull MobileTextListBlockCondition condition) {
        childConditions.add(MobileTextListBlockConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public MobileTextListBlockEmptyCondition or(@NotNull MobileTextListBlockCondition condition) {
        childConditions.add(MobileTextListBlockConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public Deque<MobileTextListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull MobileTextList element, @Nullable String hash) {

        // Формируем полную цепочку локаторов до MobileTextListBlock
        MobileLocatorChain listLocatorChain = element.getLocatorChain();
        MobileLocatorHolder listLocatorHolder = listLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        listLocatorChain.addLastLocator(element.getRequiredLocator(LI));

        // Формируем и выполняем операцию
        MobileElementOperation<Boolean> operation = MobileElementOperation.of(listLocatorChain, MobileGetIsPresentOperationType.of(element));
        MobileElementOperationResult<Boolean> operationResult = element.getMobileDeviceDispatcher().executor()
                .executeMobileElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(MobileElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(listLocatorHolder.getLocatorId());
        Set<Integer> indexes = operationResult.getResults().keySet();

        // Формируем ответ
        if (inverse) {
            return FilterResult.of(new HashSet<>(), calculatedHash);
        }
        return FilterResult.of(indexes, calculatedHash);
    }

    private MobileTextListBlockEmptyCondition inverse() {
        this.inverse = true;
        return this;
    }

}
