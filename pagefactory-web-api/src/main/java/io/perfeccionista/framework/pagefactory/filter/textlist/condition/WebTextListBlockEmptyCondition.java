package io.perfeccionista.framework.pagefactory.filter.textlist.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsPresentOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LI;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

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
    public WebTextListBlockEmptyCondition and(@NotNull WebTextListBlockCondition condition) {
        childConditions.add(WebTextListBlockConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public WebTextListBlockEmptyCondition or(@NotNull WebTextListBlockCondition condition) {
        childConditions.add(WebTextListBlockConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public Deque<WebTextListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull WebTextList element, @Nullable String hash) {

        // Формируем полную цепочку локаторов до WebTextListBlock
        WebLocatorChain listLocatorChain = element.getLocatorChain();
        WebLocatorHolder listLocatorHolder = listLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        listLocatorChain.addLastLocator(element.getRequiredLocator(LI));

        // Формируем и выполняем операцию
        WebGetIsPresentOperationType operationType = WebGetIsPresentOperationType.of(element);
        WebElementOperation<Boolean> operation = WebElementOperation.of(listLocatorChain, operationType);
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
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

    private WebTextListBlockEmptyCondition inverse() {
        this.inverse = true;
        return this;
    }

}
