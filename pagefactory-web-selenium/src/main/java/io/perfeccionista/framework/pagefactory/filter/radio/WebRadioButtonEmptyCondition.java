package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
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
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.OR;

public class WebRadioButtonEmptyCondition implements WebRadioButtonCondition {

    private final Deque<WebRadioButtonConditionHolder> childConditions = new ArrayDeque<>();

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
    public WebRadioButtonEmptyCondition inverse() {
        return this;
    }

    @Override
    public Deque<WebRadioButtonConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebRadioGroup element, @Nullable String hash) {
        WebLocatorChain locatorChain = element.getLocatorChain();
        WebLocatorHolder radioGroupLocatorHolder = locatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        WebLocatorHolder radioButtonLocatorHolder = element
                .getLocator(RADIO)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(RADIO)));
        locatorChain.addLocator(radioButtonLocatorHolder);
        GetIsPresent getIsPresentFunction = new GetIsPresent();
        JsOperation<Boolean> operation = JsOperation.of(locatorChain, getIsPresentFunction);
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        // TODO: После отладки написатть правильные сообщения об ошибке
        String returnedHash = operationResult.getJsWebLocatorProcessingResult(radioGroupLocatorHolder.getLocatorId())
                .orElseThrow(() -> new RuntimeException("Результат обработки локатора не найден"))
                .getHash()
                .orElseThrow(() -> new RuntimeException("Хэш у запрашиваемого элемента не рассчитан"));
        Set<Integer> indexes = operationResult.multipleResult().getValues().keySet();
        return WebFilterResult.of(indexes, returnedHash);
    }

}
