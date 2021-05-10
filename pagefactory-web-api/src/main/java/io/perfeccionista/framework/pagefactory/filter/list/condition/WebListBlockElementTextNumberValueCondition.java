package io.perfeccionista.framework.pagefactory.filter.list.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
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

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;

public class WebListBlockElementTextNumberValueCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final String elementPath;
    private final WebGetTextAvailable elementFrame;

    private final NumberValue<?> expectedNumberValue;

    private boolean inverse = false;

    public WebListBlockElementTextNumberValueCondition(@NotNull String elementPath, @NotNull NumberValue<?> expectedNumberValue) {
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.expectedNumberValue = expectedNumberValue;
    }

    public WebListBlockElementTextNumberValueCondition(@NotNull WebGetTextAvailable elementFrame, @NotNull NumberValue<?> expectedNumberValue) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.expectedNumberValue = expectedNumberValue;
    }

    public WebListBlockElementTextNumberValueCondition containsText() {
        return this;
    }

    public WebListBlockElementTextNumberValueCondition notContainText() {
        return this.inverse();
    }

    @Override
    public WebListBlockCondition and(@NotNull WebListBlockCondition condition) {
        childConditions.add(WebListBlockConditionHolder.of(ConditionGrouping.AND, condition));
        return this;
    }

    @Override
    public WebListBlockCondition or(@NotNull WebListBlockCondition condition) {
        childConditions.add(WebListBlockConditionHolder.of(ConditionGrouping.OR, condition));
        return this;
    }

    @Override
    public Deque<WebListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull WebList element, @Nullable String hash) {

        // Цепочка от корня страницы до WebListBlock
        WebLocatorChain listLocatorChain = element.getLocatorChain();
        WebLocatorHolder listLocatorHolder = listLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        listLocatorChain.addLastLocator(element.getRequiredLocator(LI));

        // Находим необходимый элемент, заданный по пути или по методу
        WebGetTextAvailable elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getWebListFrame()
                    .getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebGetTextAvailable.class);
        } else {
            elementToFilter = element.getWebListFrame()
                    .getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebGetTextAvailable.class);
        }

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetTextOperationType operationType = WebGetTextOperationType.of(elementToFilter);
        WebElementOperation<String> operation = WebElementOperationHandler.of(elementToFilter, operationType, TEXT)
                .getOperation();
        operation.getLocatorChain()
                .addFirstLocators(listLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(listLocatorHolder.getLocatorId());
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

    private WebListBlockElementTextNumberValueCondition inverse() {
        inverse = true;
        return this;
    }

}
