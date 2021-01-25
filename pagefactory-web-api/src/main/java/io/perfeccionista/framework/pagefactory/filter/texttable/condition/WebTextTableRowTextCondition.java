package io.perfeccionista.framework.pagefactory.filter.texttable.condition;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebTableFrame;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetTextOperationType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionGrouping.OR;

public class WebTextTableRowTextCondition implements WebTextTableRowCondition {

    private final Deque<WebTextTableRowConditionHolder> childConditions = new ArrayDeque<>();

    private final String columnName;

    private final String expectedText;

    private boolean inverse = false;

    public WebTextTableRowTextCondition(@NotNull String columnName, @NotNull String expectedText) {
        this.columnName = columnName;
        this.expectedText = expectedText;
    }

    public WebTextTableRowTextCondition containsTextCell() {
        return this;
    }

    public WebTextTableRowTextCondition notContainTextCell() {
        return this.inverse();
    }

    @Override
    public WebTextTableRowCondition and(@NotNull WebTextTableRowCondition condition) {
        childConditions.add(WebTextTableRowConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public WebTextTableRowCondition or(@NotNull WebTextTableRowCondition condition) {
        childConditions.add(WebTextTableRowConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public Deque<WebTextTableRowConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull FilterResult process(@NotNull WebTextTable element, @Nullable String hash) {
        WebTableFrame<DefaultWebTextBlock> webTableRegistry = element.getWebTextTableFrame();

        // Цепочка от корня страницы до WebTable column body
        WebLocatorChain tableLocatorChain = element.getLocatorChain();
        WebLocatorHolder tableLocatorHolder = tableLocatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        tableLocatorChain.addLastLocator(element.getRequiredLocator(TBODY_ROW))
                .addLastLocator(webTableRegistry.getRequiredBodyLocator(columnName));

        // Находим необходимый элемент, заданный по пути или по методу
        WebLink elementToFilter = webTableRegistry.getRequiredBodyMappedBlock(columnName).textLink();

        // Добавляем в цепочку локаторов операции локаторы до ячейки таблицы
        WebGetTextOperationType operationType = WebGetTextOperationType.of(elementToFilter);
        WebElementOperation<String> operation = WebElementOperationHandler.of(elementToFilter, operationType, TEXT)
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
        Map<Integer, String> textValues = operationResult.getResults();
        Set<Integer> matches = getMatches(textValues);

        // Формируем ответ
        return FilterResult.of(matches, calculatedHash);
    }

    private Set<Integer> getMatches(Map<Integer, String> textValues) {
        Set<Integer> matches = new HashSet<>();
        textValues.forEach((key, value) -> {
            boolean check = expectedText.equals(value);
            if ((check && !inverse) || (!check && inverse)) {
                matches.add(key);
            }
        });
        return matches;
    }

    private WebTextTableRowTextCondition inverse() {
        inverse = true;
        return this;
    }

}
