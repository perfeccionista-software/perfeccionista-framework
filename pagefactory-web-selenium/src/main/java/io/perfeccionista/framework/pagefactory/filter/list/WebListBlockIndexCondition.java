package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.pagefactory.jsfunction.GetIsPresent;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.value.number.NumberValue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.OR;

public class WebListBlockIndexCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final NumberValue<Integer> value;

    private boolean inverse = false;

    public WebListBlockIndexCondition(NumberValue<Integer> value) {
        this.value = value;
    }

    @Override
    public WebListBlockCondition and(WebListBlockCondition condition) {
        childConditions.add(WebListBlockConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public WebListBlockCondition or(WebListBlockCondition condition) {
        childConditions.add(WebListBlockConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public WebListBlockCondition inverse() {
        this.inverse = true;
        return this;
    }

    @Override
    public Deque<WebListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public WebConditionProcessingResult process(WebList element, String hash) {
        // TODO: Обработать inverse
        WebLocatorChain locatorChain = element.getLocatorChain();
        WebLocatorHolder listLocatorHolder = locatorChain.getLastLocator();
        listLocatorHolder.setCalculateHash(true);
        if (hash != null) {
            listLocatorHolder.setExpectedHash(hash);
        }
        WebLocatorHolder liLocatorHolder = element
                .getLocator(WebComponents.LI)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(LI)));
        locatorChain.addLocator(liLocatorHolder);
        GetIsPresent getIsPresentFunction = new GetIsPresent();
        JsOperation<Boolean> operation = JsOperation.of(locatorChain, getIsPresentFunction);
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        Set<Integer> indexes = operationResult.multipleResult().getValues().keySet();
        // TODO: После отладки написатть правильные сообщения об ошибке

        // TODO: Проверить индексы по value

        String returnedHash = operationResult.getJsWebLocatorProcessingResult(listLocatorHolder.getLocatorId())
                .orElseThrow(() -> new RuntimeException("Результат обработки локатора не найден"))
                .getHash()
                .orElseThrow(() -> new RuntimeException("Хэш у запрашиваемого элемента не рассчитан"));
        return WebConditionProcessingResult.of(indexes, returnedHash);
    }

}
