package io.perfeccionista.framework.pagefactory.filter.conditions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.WebElementIsDisplayedOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsDisplayedOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.DISPLAYED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

public class WebItemElementComponentDisplayedCondition<T extends WebBlock<?>> implements WebItemCondition<T> {

    private final Deque<WebListItemConditionHolder<T>> childConditions = new ArrayDeque<>();

    private final String elementPath;
    private final WebChildElement elementFrame;
    private final String componentName;

    private boolean inverse = false;

    public WebItemElementComponentDisplayedCondition(@NotNull String elementPath, @NotNull String componentName) {
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.componentName = componentName;
    }

    public WebItemElementComponentDisplayedCondition(@NotNull WebChildElement elementFrame, @NotNull String componentName) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.componentName = componentName;
    }

    public WebItemElementComponentDisplayedCondition<T> componentDisplayed() {
        return this;
    }

    public WebItemElementComponentDisplayedCondition<T> componentNotDisplayed() {
        return this.inverse();
    }

    @Override
    public WebItemCondition<T> and(@NotNull WebItemCondition<T> condition) {
        childConditions.add(WebListItemConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public WebItemCondition<T> or(@NotNull WebItemCondition<T> condition) {
        childConditions.add(WebListItemConditionHolder.of(OR, condition));
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
        WebChildElement elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getItemFrame()
                    .getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebChildElement.class);
        } else {
            elementToFilter = element.getItemFrame()
                    .getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), elementFrame.getElementIdentifier().getElementType());
        }

        // Проверяем, что явно заданный локатор присутствует
        elementToFilter.getRequiredSelector(componentName);

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetIsDisplayedOperationType operationType = WebGetIsDisplayedOperationType.of(elementToFilter, DISPLAYED);
        WebElementOperation<Boolean> operation = WebElementIsDisplayedOperationHandler.of(elementToFilter, operationType, componentName)
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
        Map<Integer, Boolean> displayedValues = operationResult.getResults();
        Set<Integer> matches = getMatches(displayedValues);

        // Формируем ответ
        return FilterResult.of(matches, calculatedHash);
    }

    private Set<Integer> getMatches(Map<Integer, Boolean> presentValues) {
        Set<Integer> matches = new HashSet<>();
        presentValues.forEach((key, value) -> {
            if ((!inverse && value != null && value) || (inverse && value == null) || (inverse && !value)) {
                matches.add(key);
            }
        });
        return matches;
    }

    private WebItemElementComponentDisplayedCondition<T> inverse() {
        inverse = true;
        return this;
    }

}
