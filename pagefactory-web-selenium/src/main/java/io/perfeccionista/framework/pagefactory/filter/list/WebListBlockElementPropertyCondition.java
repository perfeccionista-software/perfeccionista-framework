package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementPropertyNotDeclaredException;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
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
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_PROPERTY_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.utils.ReflectionUtils.readField;

public class WebListBlockElementPropertyCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final String elementName;
    private WebChildElement elementMock;

    private final String propertyName;
    private final StringValue stringValue;
    private final NumberValue<? extends Number> numberValue;

    private boolean inverse = false;

    public WebListBlockElementPropertyCondition(WebChildElement elementMock, String propertyName, StringValue stringValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.propertyName = propertyName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebListBlockElementPropertyCondition(WebChildElement elementMock, String propertyName, NumberValue<? extends Number> numberValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.propertyName = propertyName;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebListBlockElementPropertyCondition(String elementName, String propertyName, StringValue stringValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.propertyName = propertyName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebListBlockElementPropertyCondition(String elementName, String propertyName, NumberValue<? extends Number> numberValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.propertyName = propertyName;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    @Override
    public WebListBlockCondition and(@NotNull WebListBlockCondition condition) {
        childConditions.add(WebListBlockConditionHolder.of(ConditionUsage.AND, condition));
        return this;
    }

    @Override
    public WebListBlockCondition or(@NotNull WebListBlockCondition condition) {
        childConditions.add(WebListBlockConditionHolder.of(ConditionUsage.OR, condition));
        return this;
    }

    @Override
    public WebListBlockElementPropertyCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public WebFilterResult process(@NotNull WebList element, @Nullable String hash) {
        // TODO: Переписать эту логику!!! Она работает, но тут много лишнего.
        if (elementMock == null) {
            Class<? extends WebMappedBlock> mappedBlockClass = readField("mappedBlockClass", element);
            elementMock = WebMappedBlock.from(mappedBlockClass).getElementRegistry().getElementByPath(elementName)
                    .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage(elementName)));
        }
        WebElementPropertyHolder webElementPropertyHolder = elementMock.getProperty(propertyName)
                .orElseThrow(() -> new ElementPropertyNotDeclaredException(ELEMENT_PROPERTY_NOT_DECLARED.getMessage(propertyName)));
        JsOperation<String> operation = webElementPropertyHolder.getPropertyExtractor()
                .getJsOperation(elementMock, webElementPropertyHolder.getLocatorHolder());
        WebLocatorChain locatorChainToListBlock = element.getLocatorChain();
        WebLocatorHolder listLocatorHolder = locatorChainToListBlock.getLastLocator()
                .setCalculateHash(true)
                .setExpectedHash(hash);
        WebLocatorHolder listBlockLocatorHolder = element
                .getLocator(WebComponents.LI)
                .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(LI))
                        .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson())));
        locatorChainToListBlock.addLocator(listBlockLocatorHolder);
        WebLocatorChain locatorChainToTextValues = operation.getLocatorChain();
        locatorChainToTextValues.addLocatorsToTop(locatorChainToListBlock.getAllLocators());
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
        operationResult.ifException(exception -> {
            throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
        });
        Map<Integer, String> textValues = operationResult.multipleResult().getValues();
        String returnedHash = operationResult.getJsWebLocatorProcessingResult(listLocatorHolder.getLocatorId())
                .orElseThrow(() -> new RuntimeException("Результат обработки локатора не найден"))
                .getHash()
                .orElseThrow(() -> new RuntimeException("Хэш у запрашиваемого элемента не рассчитан"));
        return WebFilterResult.of(getMatches(textValues), returnedHash);
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
