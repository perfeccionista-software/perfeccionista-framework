package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.exceptions.TableColumnNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.TableColumnHolder;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.jsfunction.JsFunction;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_TABLE_COLUMN_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.TABLE_COLUMN_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.OR;
import static io.perfeccionista.framework.utils.ReflectionUtils.readField;

public class WebTextTableRowTextCondition implements WebTextTableRowCondition {

    private final Deque<WebTextTableRowConditionHolder> childConditions = new ArrayDeque<>();

    private final String columnName;
    private final StringValue stringValue;
    private final NumberValue<?> numberValue;

    private boolean inverse = false;

    public WebTextTableRowTextCondition(String columnName, StringValue stringValue) {
        this.columnName = columnName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebTextTableRowTextCondition(String columnName, NumberValue<?> numberValue) {
        this.columnName = columnName;
        this.stringValue = null;
        this.numberValue = numberValue;
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
    public WebTextTableRowTextCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebTextTableRowConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebTextTable element, @Nullable String hash) {
        WebLocatorChain locatorChain = element.getLocatorChain();
        WebLocatorHolder textTableLocatorHolder = locatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        WebLocatorHolder textTableRowLocatorHolder = element
                .getLocator(TBODY_ROW)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(TBODY_ROW)));
        locatorChain.addLocator(textTableRowLocatorHolder);
        // TODO: Переписать эту логику!!! Она работает, но тут много лишнего.
        Map<String, TableColumnHolder> tableColumnHolders = readField("tableColumnHolders", element);
        TableColumnHolder tableColumnHolder = Optional.ofNullable(tableColumnHolders.get(columnName))
                .orElseThrow(() -> new TableColumnNotDeclaredException(
                        TABLE_COLUMN_NOT_DECLARED.getMessage(columnName, element.getElementIdentifier().getLastUsedName())));
        WebLocatorHolder tableColumnLocator = tableColumnHolder.getBodyLocator()
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_TABLE_COLUMN_LOCATOR_NOT_DECLARED.getMessage(columnName)));
        locatorChain.addLocator(tableColumnLocator);
        JsFunction<String> getTextFunction = element.getActionImplementation(GET_TEXT_METHOD, String.class)
                .getJsOperation(element).get()
                .getEndpointJsFunction();
        JsOperation<String> operation = JsOperation.of(locatorChain, getTextFunction);
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        // TODO: После отладки написатть правильные сообщения об ошибке
        String returnedHash = operationResult.getJsWebLocatorProcessingResult(textTableLocatorHolder.getLocatorId())
                .orElseThrow(() -> new RuntimeException("Результат обработки локатора не найден"))
                .getHash()
                .orElseThrow(() -> new RuntimeException("Хэш у запрашиваемого элемента не рассчитан"));
        Map<Integer, String> indexes = operationResult.multipleResult().getValues();
        return WebFilterResult.of(getMatches(indexes), returnedHash);
    }

    private Set<Integer> getMatches(Map<Integer, String> textValues) {
        Set<Integer> matches = new HashSet<>();
        textValues.forEach((key, value) -> {
            boolean check;
            if (stringValue != null) {
                check = stringValue.check(value);
            } else {
                //noinspection ConstantConditions
                check = numberValue.checkString(value);
            }
            if ((check && !inverse) || (!check && inverse)) {
                matches.add(key);
            }
        });
        return matches;
    }

}
