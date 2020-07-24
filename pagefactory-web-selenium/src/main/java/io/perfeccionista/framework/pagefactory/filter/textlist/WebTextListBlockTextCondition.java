package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.exceptions.TableColumnNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
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
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.OR;
import static io.perfeccionista.framework.utils.ReflectionUtils.readField;

public class WebTextListBlockTextCondition implements WebTextListBlockCondition {

    private final Deque<WebTextListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final StringValue stringValue;
    private final NumberValue<? extends Number> numberValue;

    private boolean inverse = false;

    public WebTextListBlockTextCondition(StringValue stringValue) {
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebTextListBlockTextCondition(NumberValue<? extends Number> numberValue) {
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    @Override
    public WebTextListBlockIndexCondition and(@NotNull WebTextListBlockCondition condition) {
        childConditions.add(WebTextListBlockConditionHolder.of(AND, condition));
        return null;
    }

    @Override
    public WebTextListBlockIndexCondition or(@NotNull WebTextListBlockCondition condition) {
        childConditions.add(WebTextListBlockConditionHolder.of(OR, condition));
        return null;
    }

    @Override
    public WebTextListBlockTextCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebTextListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebTextList element, @Nullable String hash) {
        WebLocatorChain locatorChain = element.getLocatorChain();
        WebLocatorHolder textListLocatorHolder = locatorChain.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        WebLocatorHolder textListBlockLocatorHolder = element
                .getLocator(LI)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(LI)));
        locatorChain.addLocator(textListBlockLocatorHolder);
        // TODO: Переписать эту логику!!! Она работает, но тут много лишнего.
        JsFunction<String> getTextFunction = element.getActionImplementation(GET_TEXT_METHOD, String.class)
                .getJsOperation(element).get()
                .getEndpointJsFunction();
        JsOperation<String> operation = JsOperation.of(locatorChain, getTextFunction);
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        // TODO: После отладки написатть правильные сообщения об ошибке
        String returnedHash = operationResult.getJsWebLocatorProcessingResult(textListLocatorHolder.getLocatorId())
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
