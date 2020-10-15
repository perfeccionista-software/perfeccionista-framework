package io.perfeccionista.framework.pagefactory.filter.radio.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsPresent;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.OR;

public class WebRadioButtonEmptyCondition implements WebRadioButtonCondition {

    private final Deque<WebRadioButtonConditionHolder> childConditions = new ArrayDeque<>();

    private boolean inverse = false;

    public WebRadioButtonEmptyCondition allRadioButtons() {
        return this;
    }

    public WebRadioButtonEmptyCondition noRadioButtons() {
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
    public @NotNull WebFilterResult process(@NotNull WebRadioGroup element, @Nullable String hash) {

        // Формируем полную цепочку локаторов до WebRadioButtonBlock
        WebLocatorChain radioGroupLocatorChain = element.getLocatorChain();
        WebLocatorHolder radioGroupLocatorHolder = radioGroupLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        radioGroupLocatorChain.addLastLocator(element.getRequiredLocator(RADIO));

        // Формируем и выполняем операцию
        JsOperation<Boolean> jsOperation = JsOperation.of(radioGroupLocatorChain, new GetIsPresent());
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(jsOperation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(radioGroupLocatorHolder.getLocatorId());
        Set<Integer> indexes = operationResult.getResults().keySet();

        // Формируем ответ
        if (inverse) {
            return WebFilterResult.of(new HashSet<>(), calculatedHash);
        }
        return WebFilterResult.of(indexes, calculatedHash);
    }

    private WebRadioButtonEmptyCondition inverse() {
        inverse = true;
        return this;
    }

}
