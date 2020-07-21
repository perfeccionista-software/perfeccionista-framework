package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementActionNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementNotDeclaredException;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionRegistry;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_ACTION_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.RADIO;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.factory.WebPageFactory.getWebChildElementMethods;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementActionAnnotationHandler.createWebElementActionRegistryFor;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.OR;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyRadioButtonFilter;
import static io.perfeccionista.framework.utils.ReflectionUtils.readField;

public class WebRadioButtonLabelCondition implements WebRadioButtonCondition {

    private final Deque<WebRadioButtonConditionHolder> childConditions = new ArrayDeque<>();

    private final StringValue stringValue;
    private final NumberValue<? extends Number> numberValue;

    private boolean inverse = false;

    public WebRadioButtonLabelCondition(StringValue stringValue) {
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebRadioButtonLabelCondition(NumberValue<? extends Number> numberValue) {
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    @Override
    public WebRadioButtonCondition and(@NotNull WebRadioButtonCondition condition) {
        childConditions.add(WebRadioButtonConditionHolder.of(AND, condition));
        return this;
    }

    @Override
    public WebRadioButtonCondition or(@NotNull WebRadioButtonCondition condition) {
        childConditions.add(WebRadioButtonConditionHolder.of(OR, condition));
        return this;
    }

    @Override
    public WebRadioButtonLabelCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebRadioButtonConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebRadioGroup element, @Nullable String hash) {
        Class<? extends WebMappedBlock> mappedBlockClass = readField("mappedBlockClass", element);
        List<Method> radioButtonMethodList = getWebChildElementMethods(mappedBlockClass).stream()
                .filter(method -> "radioButton".equals(method.getName()))
                .filter(method -> WebRadioButton.class.isAssignableFrom(method.getReturnType()))
                .collect(Collectors.toList());
        if (radioButtonMethodList.size() != 1) {
            throw new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage("radioButton"));
        }
        Method webRadioButtonMethod = radioButtonMethodList.get(0);
        WebRadioButton radioButtonMock = WebMappedBlock.from(mappedBlockClass).getElementRegistry()
                .getElementByMethod(webRadioButtonMethod, WebRadioButton.class)
                .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage(webRadioButtonMethod.getName())));

        Class<? extends AbstractWebChildElement> elementImplementationClass = element.getEnvironment()
                .getService(WebPageService.class)
                .getConfiguration()
                .getElementsConfiguration()
                .getElementImplementations()
                .get(WebRadioButton.class);

        WebElementActionRegistry webElementActionRegistry = createWebElementActionRegistryFor(elementImplementationClass);

        Optional<JsOperation<String>> optionalStringJsOperation = webElementActionRegistry.getAction(GET_LABEL_METHOD, String.class)
                .orElseThrow(() -> new ElementActionNotDeclaredException(ELEMENT_ACTION_NOT_DECLARED.getMessage(GET_LABEL_METHOD)))
                .getJsOperation(radioButtonMock);

        if (optionalStringJsOperation.isPresent()) {
            WebLocatorChain locatorChainToRadioGroup = element.getLocatorChain();
            WebLocatorHolder radioGroupLocatorHolder = locatorChainToRadioGroup.getLastLocator()
                    .setCalculateHash(true)
                    .setExpectedHash(hash);
            WebLocatorHolder radioButtonLocatorHolder = element
                    .getLocator(RADIO)
                    .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(RADIO))
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson())));

            locatorChainToRadioGroup.addLocator(radioButtonLocatorHolder);
            JsOperation<String> operation = optionalStringJsOperation.get();
            WebLocatorChain locatorChainToTextValues = operation.getLocatorChain();
            locatorChainToTextValues.addLocatorsToTop(locatorChainToRadioGroup.getAllLocators());
            JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
            operationResult.ifException(exception -> {
                throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
            });
            Map<Integer, String> labelValues = operationResult.multipleResult().getValues();
            String returnedHash = operationResult.getJsWebLocatorProcessingResult(radioGroupLocatorHolder.getLocatorId())
                    .orElseThrow(() -> new RuntimeException("Результат обработки локатора не найден"))
                    .getHash()
                    .orElseThrow(() -> new RuntimeException("Хэш у запрашиваемого элемента не рассчитан"));
            return WebFilterResult.of(getMatches(labelValues), returnedHash);
        } else {
            WebFilterResult filterResult = element.filter(emptyRadioButtonFilter())
                    .setInitialHash(hash)
                    .getResult();
            WebPageFactory webPageFactory = element.getEnvironment().getService(WebPageService.class).getWebPageFactory();
            Map<Integer, WebRadioButton> webRadioButtons = webPageFactory.createWebRadioButtons(element, filterResult);
            Map<Integer, String> labelValues = new HashMap<>();
            for (Entry<Integer, WebRadioButton> webMappedBlockEntry : webRadioButtons.entrySet()) {
                String label = webMappedBlockEntry.getValue().getLabel();
                labelValues.put(webMappedBlockEntry.getKey(), label);
            }
            String returnedHash = filterResult.getHash();
            return WebFilterResult.of(getMatches(labelValues), returnedHash);
        }
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
