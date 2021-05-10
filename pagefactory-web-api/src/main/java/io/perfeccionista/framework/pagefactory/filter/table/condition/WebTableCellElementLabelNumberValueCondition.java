package io.perfeccionista.framework.pagefactory.filter.table.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetLabelOperationType;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

public class WebTableCellElementLabelNumberValueCondition implements WebTableRowCondition {

    private final Deque<WebTableRowConditionHolder> childConditions = new ArrayDeque<>();

    private final String columnName;
    private final String elementPath;
    private final WebGetLabelAvailable elementFrame;

    private final NumberValue<?> expectedNumberValue;

    private boolean inverse = false;

    public WebTableCellElementLabelNumberValueCondition(@NotNull String columnName,
                                                        @NotNull String elementPath,
                                                        @NotNull NumberValue<?> expectedNumberValue) {
        this.columnName = columnName;
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.expectedNumberValue = expectedNumberValue;
    }

    public WebTableCellElementLabelNumberValueCondition(@NotNull String columnName,
                                                        @NotNull WebGetLabelAvailable elementFrame,
                                                        @NotNull NumberValue<?> expectedNumberValue) {
        this.columnName = columnName;
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.expectedNumberValue = expectedNumberValue;
    }

    public WebTableCellElementLabelNumberValueCondition containsLabel() {
        return this;
    }

    public WebTableCellElementLabelNumberValueCondition notContainLabel() {
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
    public @NotNull FilterResult process(@NotNull WebTable element, @Nullable String hash) {
        WebTableFrame<WebBlock> webTableRegistry = element.getWebTableFrame();

        // Цепочка от корня страницы до WebTable column body
        WebLocatorChain tableLocatorChain = element.getLocatorChain();
        WebLocatorHolder tableLocatorHolder = tableLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        tableLocatorChain.addLastLocator(element.getRequiredLocator(TBODY_ROW))
                .addLastLocator(webTableRegistry.getRequiredBodyLocator(columnName));

        // Находим необходимый элемент, заданный по пути или по методу
        WebGetLabelAvailable elementToFilter;
        if (elementPath != null) {
            elementToFilter = webTableRegistry.getRequiredBodyMappedBlock(columnName)
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebGetLabelAvailable.class);
        } else {
            elementToFilter = webTableRegistry.getRequiredBodyMappedBlock(columnName)
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebGetLabelAvailable.class);
        }

        // Добавляем в цепочку локаторов операции локаторы до ячейки таблицы
        WebGetLabelOperationType operationType = WebGetLabelOperationType.of(elementToFilter);
        WebElementOperation<String> operation = WebElementOperationHandler.of(elementToFilter, operationType, LABEL)
                .getOperation();
        operation.getLocatorChain()
                .addFirstLocators(tableLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        // Разбираем полученные значения
        String calculatedHash = operationResult.getRequiredHash(tableLocatorHolder.getLocatorId());
        Map<Integer, String> labelValues = operationResult.getResults();
        Set<Integer> matches = getMatches(labelValues);

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

    private WebTableCellElementLabelNumberValueCondition inverse() {
        inverse = true;
        return this;
    }

}
