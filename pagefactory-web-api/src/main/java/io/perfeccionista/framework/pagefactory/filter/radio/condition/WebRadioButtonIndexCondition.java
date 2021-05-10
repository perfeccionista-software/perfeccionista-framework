package io.perfeccionista.framework.pagefactory.filter.radio.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
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

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.RADIO;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

public class WebRadioButtonIndexCondition implements WebRadioButtonCondition {

    private final Deque<WebRadioButtonConditionHolder> childConditions = new ArrayDeque<>();

    private final Integer expectedIndex;
    private final NumberValue<Integer> expectedValue;

    private boolean inverse = false;

    public WebRadioButtonIndexCondition(@NotNull Integer expectedIndex) {
        this.expectedIndex = expectedIndex;
        this.expectedValue = null;
    }

    public WebRadioButtonIndexCondition(@NotNull NumberValue<Integer> expectedValue) {
        this.expectedIndex = null;
        this.expectedValue = expectedValue;
    }

    public WebRadioButtonIndexCondition withRadioButtonIndex() {
        return this;
    }

    public WebRadioButtonIndexCondition withoutRadioButtonIndex() {
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

        // Формируем полную цепочку локаторов до WebRadioButtonBlock
        WebLocatorChain radioGroupLocatorChain = element.getLocatorChain();
        WebLocatorHolder radioGroupLocatorHolder = radioGroupLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        radioGroupLocatorChain.addLastLocator(element.getRequiredLocator(RADIO));

        // Формируем и выполняем операцию
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(element);
        WebElementOperation<Boolean> operation = WebElementOperation.of(radioGroupLocatorChain, operationType);
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(radioGroupLocatorHolder.getLocatorId());
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

    private WebRadioButtonIndexCondition inverse() {
        inverse = true;
        return this;
    }

}
