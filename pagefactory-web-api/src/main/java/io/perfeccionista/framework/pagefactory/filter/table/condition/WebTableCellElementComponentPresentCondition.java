package io.perfeccionista.framework.pagefactory.filter.table.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping.OR;

public class WebTableCellElementComponentPresentCondition implements WebTableRowCondition {

    private final Deque<WebTableRowConditionHolder> childConditions = new ArrayDeque<>();

    private final String columnName;
    private final String elementPath;
    private final WebChildElement elementFrame;
    private final String componentName;

    private boolean inverse = false;

    public WebTableCellElementComponentPresentCondition(@NotNull String columnName,
                                                        @NotNull String elementPath,
                                                        @NotNull String componentName) {
        this.columnName = columnName;
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.componentName = componentName;
    }

    public WebTableCellElementComponentPresentCondition(@NotNull String columnName,
                                                        @NotNull WebChildElement elementFrame,
                                                        @NotNull String componentName) {
        this.columnName = columnName;
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.componentName = componentName;
    }

    public WebTableCellElementComponentPresentCondition componentPresent() {
        return this;
    }

    public WebTableCellElementComponentPresentCondition componentNotPresent() {
        return this.inverse();
    }

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
    public Deque<WebTableRowConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebTable element, @Nullable String hash) {
        WebTableFrame<WebBlock> webTableRegistry = element.getWebTableFrame();

        // Цепочка от корня страницы до WebTable column body
        WebLocatorChain tableLocatorChain = element.getLocatorChain();
        WebLocatorHolder tableLocatorHolder = tableLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        tableLocatorChain.addLastLocator(element.getRequiredLocator(TBODY_ROW))
                .addLastLocator(webTableRegistry.getRequiredBodyLocator(columnName));

        // Находим необходимый элемент, заданный по пути или по методу
        WebChildElement elementToFilter;
        if (elementPath != null) {
            elementToFilter = webTableRegistry.getRequiredBodyMappedBlock(columnName)
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebChildElement.class);
        } else {
            elementToFilter = webTableRegistry.getRequiredBodyMappedBlock(columnName)
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), elementFrame.getElementIdentifier().getElementType());
        }

        // Проверяем, что явно заданный локатор присутствует
        elementToFilter.getRequiredLocator(componentName);

        // Добавляем в цепочку локаторов операции локаторы до ячейки таблицы
        JsOperation<Boolean> jsOperation = elementToFilter
                .getJsOperationActionImplementation(IS_PRESENT_METHOD, Boolean.class)
                .getJsOperation(elementToFilter, componentName);
        jsOperation.getLocatorChain()
                .addFirstLocators(tableLocatorChain);

        // Выполняем операцию
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(jsOperation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(tableLocatorHolder.getLocatorId());
        Map<Integer, Boolean> presentValues = operationResult.getResults();
        Set<Integer> matches = getMatches(presentValues);

        // Формируем ответ
        return WebFilterResult.of(matches, calculatedHash);
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

    private WebTableCellElementComponentPresentCondition inverse() {
        inverse = true;
        return this;
    }

}
