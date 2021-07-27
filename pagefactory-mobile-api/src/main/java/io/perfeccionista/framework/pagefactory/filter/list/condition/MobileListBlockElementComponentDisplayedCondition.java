package io.perfeccionista.framework.pagefactory.filter.list.condition;

import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.MobileGetIsComponentDisplayedOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

public class MobileListBlockElementComponentDisplayedCondition implements MobileListBlockCondition {

    private final Deque<MobileListBlockConditionHolder> childConditions = new ArrayDeque<>();
    private final MobileChildElement elementFrame;
    private final String componentName;

    private boolean inverse = false;

    public MobileListBlockElementComponentDisplayedCondition(MobileChildElement elementFrame, String componentName) {
        this.elementFrame = elementFrame;
        this.componentName = componentName;
    }

    @Override
    public MobileListBlockCondition and(@NotNull MobileListBlockCondition condition) {
        childConditions.add(MobileListBlockConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public MobileListBlockCondition or(@NotNull MobileListBlockCondition condition) {
        childConditions.add(MobileListBlockConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public Deque<MobileListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull MobileList element, @Nullable String hash) {
        // Цепочка от корня страницы до MobileListBlock
        MobileLocatorChain listLocatorChain = element.getLocatorChain();
        MobileLocatorHolder listLocatorHolder = listLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        listLocatorChain.addLastLocator(element.getRequiredLocator(ITEM));

        MobileChildElement elementToFilter = element.getMobileListFrame()
                .getMappedBlockFrame()
                .getElementRegistry()
                .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), MobileChildElement.class);

        listLocatorChain.addLastLocators(elementToFilter.getLocatorChain());
        MobileElementOperation<Boolean> operation = MobileElementOperation.of(listLocatorChain, MobileGetIsComponentDisplayedOperationType.of(elementToFilter, componentName));
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

    public MobileListBlockElementComponentDisplayedCondition componentDisplayed() {
        return this;
    }

    public MobileListBlockElementComponentDisplayedCondition componentNotDisplayed() {
        inverse = true;
        return this;
    }
}
