package io.perfeccionista.framework.pagefactory.filter.block.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetLabelOperationType;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;

public class WebBlockElementLabelStringValueCondition<T extends WebBlock> implements WebBlockCondition<T> {

    private final Deque<WebListBlockConditionHolder<T>> childConditions = new ArrayDeque<>();

    private final String elementPath;
    private final WebChildElement elementFrame;

    private final StringValue expectedStringValue;

    private boolean inverse = false;

    public WebBlockElementLabelStringValueCondition(@NotNull String elementPath, @NotNull StringValue expectedStringValue) {
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.expectedStringValue = expectedStringValue;
    }

    public WebBlockElementLabelStringValueCondition(@NotNull WebGetLabelAvailable elementFrame, @NotNull StringValue expectedStringValue) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.expectedStringValue = expectedStringValue;
    }

    public WebBlockElementLabelStringValueCondition<T> containsLabel() {
        return this;
    }

    public WebBlockElementLabelStringValueCondition<T> notContainLabel() {
        return this.inverse();
    }

    @Override
    public WebBlockCondition<T> and(@NotNull WebBlockCondition<T> condition) {
        childConditions.add(WebListBlockConditionHolder.of(ConditionGrouping.AND, condition));
        return this;
    }

    @Override
    public WebBlockCondition<T> or(@NotNull WebBlockCondition<T> condition) {
        childConditions.add(WebListBlockConditionHolder.of(ConditionGrouping.OR, condition));
        return this;
    }

    @Override
    public Deque<WebListBlockConditionHolder<T>> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull WebList<T> element, @Nullable String hash) {

        // Цепочка от корня страницы до WebListBlock
        WebLocatorChain listLocatorChain = element.getLocatorChain();
        WebLocatorHolder listLocatorHolder = listLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        listLocatorChain.addLastLocator(element.getRequiredLocator(ITEM));

        // Находим необходимый элемент, заданный по пути или по методу
        WebGetLabelAvailable elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getBlockFrame()
                    .getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebGetLabelAvailable.class);
        } else {
            elementToFilter = element.getBlockFrame()
                    .getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebGetLabelAvailable.class);
        }

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetLabelOperationType operationType = WebGetLabelOperationType.of(elementToFilter);
        WebElementOperation<String> operation = WebElementOperationHandler.of(elementToFilter, operationType, LABEL)
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
            boolean check = expectedStringValue.check(value);
            if ((check && !inverse) || (!check && inverse)) {
                matches.add(key);
            }
        });
        return matches;
    }

    private WebBlockElementLabelStringValueCondition<T> inverse() {
        inverse = true;
        return this;
    }

}