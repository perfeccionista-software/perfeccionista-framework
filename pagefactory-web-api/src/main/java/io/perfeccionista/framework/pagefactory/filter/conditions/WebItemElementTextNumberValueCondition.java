package io.perfeccionista.framework.pagefactory.filter.conditions;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.options.GetTextOptions;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetTextOperationType;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;

public class WebItemElementTextNumberValueCondition<T extends WebBlock<?>> implements WebItemCondition<T> {

    private final Deque<WebListItemConditionHolder<T>> childConditions = new ArrayDeque<>();

    private final String elementPath;
    private final WebGetTextAvailable elementFrame;

    private final NumberValue<?> expectedNumberValue;

    private boolean inverse = false;

    public WebItemElementTextNumberValueCondition(@NotNull String elementPath, @NotNull NumberValue<?> expectedNumberValue) {
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.expectedNumberValue = expectedNumberValue;
    }

    public WebItemElementTextNumberValueCondition(@NotNull WebGetTextAvailable elementFrame, @NotNull NumberValue<?> expectedNumberValue) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.expectedNumberValue = expectedNumberValue;
    }

    public WebItemElementTextNumberValueCondition<T> containsText() {
        return this;
    }

    public WebItemElementTextNumberValueCondition<T> notContainText() {
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
        WebGetTextAvailable elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getItemFrame()
                    .getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebGetTextAvailable.class);
        } else {
            elementToFilter = element.getItemFrame()
                    .getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebGetTextAvailable.class);
        }

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetTextOperationType operationType = WebGetTextOperationType.of(elementToFilter, GetTextOptions.getTextForComponent(TEXT));
        WebElementOperation<String> operation = WebElementOperationHandler.of(elementToFilter, operationType, TEXT)
                .getOperation();
        operation.getLocatorChain()
                .addFirstSelectors(listLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(listLocatorHolder.getSelectorId());
        Map<Integer, String> textValues = operationResult.getResults();
        Set<Integer> matches = getMatches(textValues);

        // Формируем ответ
        return FilterResult.of(matches, calculatedHash);
    }

    private Set<Integer> getMatches(Map<Integer, String> textValues) {
        Set<Integer> matches = new HashSet<>();
        textValues.forEach((key, value) -> {
            boolean check = expectedNumberValue.checkString(value);
            if ((check && !inverse) || (!check && inverse)) {
                matches.add(key);
            }
        });
        return matches;
    }

    private WebItemElementTextNumberValueCondition<T> inverse() {
        inverse = true;
        return this;
    }

}
