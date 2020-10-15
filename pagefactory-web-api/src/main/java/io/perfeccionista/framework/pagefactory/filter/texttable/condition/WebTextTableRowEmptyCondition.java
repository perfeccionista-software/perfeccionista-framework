package io.perfeccionista.framework.pagefactory.filter.texttable.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
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

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.OR;

public class WebTextTableRowEmptyCondition implements WebTextTableRowCondition {

    private final Deque<WebTextTableRowConditionHolder> childConditions = new ArrayDeque<>();

    private boolean inverse = false;

    public WebTextTableRowEmptyCondition allTextRows() {
        return this;
    }

    public WebTextTableRowEmptyCondition noTextRows() {
        return this.inverse();
    }

    @Override
    public WebTextTableRowCondition and(@NotNull WebTextTableRowCondition condition) {
        childConditions.add(WebTextTableRowConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public WebTextTableRowCondition or(@NotNull WebTextTableRowCondition condition) {
        childConditions.add(WebTextTableRowConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public Deque<WebTextTableRowConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebTextTable element, @Nullable String hash) {

        // Цепочка от корня страницы до WebTable column body
        WebLocatorChain tableLocatorChain = element.getLocatorChain();
        WebLocatorHolder tableLocatorHolder = tableLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        tableLocatorChain.addLastLocator(element.getRequiredLocator(TBODY_ROW));

        // Формируем и выполняем операцию
        JsOperation<Boolean> jsOperation = JsOperation.of(tableLocatorChain, new GetIsPresent());
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(jsOperation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(tableLocatorHolder.getLocatorId());
        Set<Integer> indexes = operationResult.getResults().keySet();

        // Формируем ответ
        if (inverse) {
            return WebFilterResult.of(new HashSet<>(), calculatedHash);
        }
        return WebFilterResult.of(indexes, calculatedHash);
    }

    private WebTextTableRowEmptyCondition inverse() {
        this.inverse = true;
        return this;
    }

}

