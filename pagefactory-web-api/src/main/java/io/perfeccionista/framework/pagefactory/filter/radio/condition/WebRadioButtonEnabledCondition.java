package io.perfeccionista.framework.pagefactory.filter.radio.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsEnabledOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

public class WebRadioButtonEnabledCondition implements WebRadioButtonCondition {

    private final Deque<WebRadioButtonConditionHolder> childConditions = new ArrayDeque<>();

    private boolean inverse = false;

    public WebRadioButtonEnabledCondition enabled() {
        return this;
    }

    public WebRadioButtonEnabledCondition disabled() {
        return this.inverse();
    }

    @Override
    public WebRadioButtonCondition and(@NotNull WebRadioButtonCondition condition) {
        childConditions.add(WebRadioButtonConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public WebRadioButtonCondition or(@NotNull WebRadioButtonCondition condition) {
        childConditions.add(WebRadioButtonConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public Deque<WebRadioButtonConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull WebRadioGroup element, @Nullable String hash) {
        WebRadioButton webRadioButton = element.getBlockFrame()
                .getMappedBlockFrame()
                .radioButton();

        // Формируем полную цепочку локаторов до WebRadioButtonBlock
        WebLocatorChain radioGroupLocatorChain = element.getLocatorChain();
        WebLocatorHolder radioGroupLocatorHolder = radioGroupLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        radioGroupLocatorChain.addLastLocator(element.getRequiredLocator(ITEM));

        // Добавляем в цепочку локаторов операции локаторы до блока RadioButtonBlock
        WebGetIsEnabledOperationType operationType = WebGetIsEnabledOperationType.of(webRadioButton);
        WebElementOperation<Boolean> operation = WebElementOperationHandler.of(webRadioButton, operationType, ENABLED)
                .getOperation();
        operation.getLocatorChain()
                .addFirstLocators(radioGroupLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(radioGroupLocatorHolder.getLocatorId());
        Map<Integer, Boolean> enabledValues = operationResult.getResults();
        Set<Integer> matches = getMatches(enabledValues);

        // Формируем ответ
        return FilterResult.of(matches, calculatedHash);
    }

    private Set<Integer> getMatches(Map<Integer, Boolean> enabledValues) {
        Set<Integer> matches = new HashSet<>();
        enabledValues.forEach((key, value) -> {
            if (value != null && ((!inverse && value) || (inverse && !value))) {
                matches.add(key);
            }
        });
        return matches;
    }

    private WebRadioButtonEnabledCondition inverse() {
        inverse = true;
        return this;
    }

}
