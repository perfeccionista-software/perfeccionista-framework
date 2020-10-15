package io.perfeccionista.framework.pagefactory.filter.textlist.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
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

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.OR;

public class WebTextListBlockEmptyCondition implements WebTextListBlockCondition {

    private final Deque<WebTextListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private boolean inverse = false;

    public WebTextListBlockEmptyCondition allTextBlocks() {
        return this;
    }

    public WebTextListBlockEmptyCondition noTextBlocks() {
        return this.inverse();
    }

    @Override
    public WebTextListBlockIndexCondition and(@NotNull WebTextListBlockCondition condition) {
        childConditions.add(WebTextListBlockConditionHolder.of(AND, condition));
        return null;
    }

    @Override
    public WebTextListBlockIndexCondition or(@NotNull WebTextListBlockCondition condition) {
        childConditions.add(WebTextListBlockConditionHolder.of(OR, condition));
        return null;
    }

    @Override
    public Deque<WebTextListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebTextList element, @Nullable String hash) {

        // Формируем полную цепочку локаторов до WebTextListBlock
        WebLocatorChain listLocatorChain = element.getLocatorChain();
        WebLocatorHolder listLocatorHolder = listLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        listLocatorChain.addLastLocator(element.getRequiredLocator(LI));

        // Формируем и выполняем операцию
        JsOperation<Boolean> jsOperation = JsOperation.of(listLocatorChain, new GetIsPresent());
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(jsOperation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(listLocatorHolder.getLocatorId());
        Set<Integer> indexes = operationResult.getResults().keySet();

        // Формируем ответ
        if (inverse) {
            return WebFilterResult.of(new HashSet<>(), calculatedHash);
        }
        return WebFilterResult.of(indexes, calculatedHash);
    }

    private WebTextListBlockEmptyCondition inverse() {
        this.inverse = true;
        return this;
    }

}
