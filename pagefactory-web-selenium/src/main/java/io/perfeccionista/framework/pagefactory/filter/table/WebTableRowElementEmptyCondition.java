package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;
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
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.OR;

public class WebTableRowElementEmptyCondition implements WebTableRowCondition {

    private final Deque<WebTableRowConditionHolder> childConditions = new ArrayDeque<>();

    private boolean inverse = false;

    @Override
    public WebTableRowCondition and(@NotNull WebTableRowCondition condition) {
        childConditions.add(WebTableRowConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public WebTableRowCondition or(@NotNull WebTableRowCondition condition) {
        childConditions.add(WebTableRowConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public WebTableRowElementEmptyCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebTableRowConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebTable element, @Nullable String hash) {
        WebLocatorChain locatorChain = element.getLocatorChain();
        WebLocatorHolder tableLocatorHolder = locatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        WebLocatorHolder tableRowLocatorHolder = element
                .getLocator(WebComponents.TBODY_ROW)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(TBODY_ROW)));
        locatorChain.addLocator(tableRowLocatorHolder);
        GetIsPresent getIsPresentFunction = new GetIsPresent();
        JsOperation<Boolean> operation = JsOperation.of(locatorChain, getIsPresentFunction);
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        // TODO: После отладки написатть правильные сообщения об ошибке
        String returnedHash = operationResult.getJsWebLocatorProcessingResult(tableLocatorHolder.getLocatorId())
                .orElseThrow(() -> new RuntimeException("Результат обработки локатора не найден"))
                .getHash()
                .orElseThrow(() -> new RuntimeException("Хэш у запрашиваемого элемента не рассчитан"));
        Set<Integer> indexes = operationResult.multipleResult().getValues().keySet();
        return WebFilterResult.of(indexes, returnedHash);
    }

}