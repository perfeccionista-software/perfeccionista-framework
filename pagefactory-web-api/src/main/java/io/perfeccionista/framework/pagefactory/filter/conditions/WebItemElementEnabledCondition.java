package io.perfeccionista.framework.pagefactory.filter.conditions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
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

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;

public class WebItemElementEnabledCondition<T extends WebBlock<?>> implements WebItemCondition<T> {

    private final Deque<WebListItemConditionHolder<T>> childConditions = new ArrayDeque<>();

    private final String elementPath;
    private final WebChildElement elementFrame;

    private boolean inverse = false;

    public WebItemElementEnabledCondition(@NotNull String elementPath) {
        this.elementPath = elementPath;
        this.elementFrame = null;
    }

    public WebItemElementEnabledCondition(@NotNull WebIsEnabledAvailable elementFrame) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
    }

    public WebItemElementEnabledCondition<T> enabled() {
        return this;
    }

    public WebItemElementEnabledCondition<T> disabled() {
        return this.inverse();
    }

    @Override
    public WebItemCondition<T> and(@NotNull WebItemCondition<T> condition) {
        childConditions.add(WebListItemConditionHolder.of(ConditionGrouping.AND, condition));
        return this;
    }

    @Override
    public WebItemCondition<T> or(@NotNull WebItemCondition<T> condition) {
        childConditions.add(WebListItemConditionHolder.of(ConditionGrouping.OR, condition));
        return this;
    }

    @Override
    public Deque<WebListItemConditionHolder<T>> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull WebList<T> element, @Nullable String hash) {

        // Цепочка от корня страницы до WebListBlock
        WebSelectorChain listLocatorChain = element.getSelectorChain();
        WebSelectorHolder listLocatorHolder = listLocatorChain.getLastSelector()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        listLocatorChain.addLastSelector(element.getRequiredSelector(ITEM));

        // Находим необходимый элемент, заданный по пути или по методу
        WebIsEnabledAvailable elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getItemFrame()
                    .getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebIsEnabledAvailable.class);
        } else {
            elementToFilter = element.getItemFrame()
                    .getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebIsEnabledAvailable.class);
        }

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetIsEnabledOperationType operationType = WebGetIsEnabledOperationType.of(elementToFilter);
        WebElementOperation<Boolean> operation = WebElementOperationHandler.of(elementToFilter, operationType, ENABLED)
                .getOperation();
        operation.getLocatorChain()
                .addFirstSelectors(listLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(listLocatorHolder.getSelectorId());
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

    private WebItemElementEnabledCondition<T> inverse() {
        this.inverse = true;
        return this;
    }

}
