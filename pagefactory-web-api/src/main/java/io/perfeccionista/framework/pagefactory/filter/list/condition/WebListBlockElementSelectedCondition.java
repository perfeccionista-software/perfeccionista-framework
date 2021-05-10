package io.perfeccionista.framework.pagefactory.filter.list.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsSelectedOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.SELECTED;

public class WebListBlockElementSelectedCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final String elementPath;
    private final WebIsSelectedAvailable elementFrame;

    private boolean inverse = false;

    public WebListBlockElementSelectedCondition(@NotNull String elementPath) {
        this.elementPath = elementPath;
        this.elementFrame = null;
    }

    public WebListBlockElementSelectedCondition(@NotNull WebIsSelectedAvailable elementFrame) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
    }

    public WebListBlockElementSelectedCondition selected() {
        return this;
    }

    public WebListBlockElementSelectedCondition notSelected() {
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
        WebIsSelectedAvailable elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getWebListFrame()
                    .getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebIsSelectedAvailable.class);
        } else {
            elementToFilter = element.getWebListFrame()
                    .getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebIsSelectedAvailable.class);
        }

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetIsSelectedOperationType operationType = WebGetIsSelectedOperationType.of(elementToFilter);
        WebElementOperation<Boolean> operation = WebElementOperationHandler.of(elementToFilter, operationType, SELECTED)
                .getOperation();
        operation.getLocatorChain()
                .addFirstLocators(listLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(listLocatorHolder.getLocatorId());
        Map<Integer, Boolean> displayedValues = operationResult.getResults();
        Set<Integer> matches = getMatches(displayedValues);

        // Формируем ответ
        return FilterResult.of(matches, calculatedHash);
    }

    private Set<Integer> getMatches(Map<Integer, Boolean> selectedValues) {
        Set<Integer> matches = new HashSet<>();
        selectedValues.forEach((key, value) -> {
            if (value != null && ((!inverse && value) || (inverse && !value))) {
                matches.add(key);
            }
        });
        return matches;
    }

    private WebListBlockElementSelectedCondition inverse() {
        inverse = true;
        return this;
    }

}

