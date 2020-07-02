package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementNotDeclaredException;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter.WebListBlockConditionHolder;
import io.perfeccionista.framework.pagefactory.jsfunction.GetText;
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
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.utils.ReflectionUtils.readField;

public class WebListBlockElementTextCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final GetTextAvailable elementMock;
    private final String elementName;

    private final StringValue stringValue;
    private final NumberValue<?> numberValue;

    public WebListBlockElementTextCondition(GetTextAvailable elementMock, StringValue stringValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebListBlockElementTextCondition(GetTextAvailable elementMock, NumberValue<?> numberValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebListBlockElementTextCondition(String elementName, StringValue stringValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebListBlockElementTextCondition(String elementName, NumberValue<?> numberValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebListBlockElementTextCondition inverse() {
        return this;
    }

    @Override
    public WebListBlockCondition and(WebListBlockCondition condition) {

        return this;
    }

    @Override
    public WebListBlockCondition or(WebListBlockCondition condition) {

        return this;
    }

    @Override
    public Deque<WebListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public WebConditionProcessingResult process(@NotNull WebList element, @Nullable String hash) {
        WebLocatorChain locatorChain = element.getLocatorChain();
        WebLocatorHolder listLocatorHolder = locatorChain.getLastLocator();
        listLocatorHolder.setCalculateHash(true);
        if (hash != null) {
            listLocatorHolder.setExpectedHash(hash);
        }
        WebLocatorHolder liLocatorHolder = element
                .getLocator(WebComponents.LI)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(LI)));
        locatorChain.addLocator(liLocatorHolder);

        if (elementMock == null) {
            Class<? extends WebMappedBlock> mappedBlockClass = readField("mappedBlockClass", element);
            WebChildElement mappedElement = WebMappedBlock.from(mappedBlockClass).getElementRegistry().getElementByPath(elementName)
                    .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage(elementName)));
            locatorChain.addLocators(mappedElement.getLocatorChain().getAllLocators());
        } else {
            locatorChain.addLocators(elementMock.getLocatorChain().getAllLocators());
        }

        GetText getTextFunction = new GetText();
        JsOperation<String> operation = JsOperation.of(locatorChain, getTextFunction);
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        Map<Integer, String> values = operationResult.multipleResult().getValues();
        Set<Integer> matches = new HashSet<>();
        if (stringValue == null) {
            values.entrySet().forEach(entry -> {
                if (numberValue.checkString(entry.getValue())) {
                    matches.add(entry.getKey());
                }
            });
        } else {
            values.entrySet().forEach(entry -> {
                if (stringValue.check(entry.getValue())) {
                    matches.add(entry.getKey());
                }
            });
        }
        // TODO: После отладки написатть правильные сообщения об ошибке
        String returnedHash = operationResult.getJsWebLocatorProcessingResult(listLocatorHolder.getLocatorId())
                .orElseThrow(() -> new RuntimeException("Результат обработки локатора не найден"))
                .getHash()
                .orElseThrow(() -> new RuntimeException("Хэш у запрашиваемого элемента не рассчитан"));
        return WebConditionProcessingResult.of(matches, returnedHash);
    }
}
