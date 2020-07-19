package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementActionNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementNotDeclaredException;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_ACTION_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.ELEMENT_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.PRESENTED;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementActionAnnotationHandler.createWebElementActionRegistryFor;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyListFilter;
import static io.perfeccionista.framework.utils.ReflectionUtils.readField;

public class WebListBlockElementPresentCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final String elementName;
    private WebChildElement elementMock;

    private boolean inverse = false;

    public WebListBlockElementPresentCondition(IsPresentAvailable elementMock) {
        this.elementName = null;
        this.elementMock = (WebChildElement) elementMock;
    }

    public WebListBlockElementPresentCondition(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
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
    public WebListBlockElementPresentCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public WebFilterResult process(@NotNull WebList element, @Nullable String hash) {
        if (elementMock == null) {
            Class<? extends WebMappedBlock> mappedBlockClass = readField("mappedBlockClass", element);
            elementMock = WebMappedBlock.from(mappedBlockClass).getElementRegistry().getElementByPath(elementName)
                    .orElseThrow(() -> new ElementNotDeclaredException(ELEMENT_NOT_DECLARED.getMessage(elementName)));
        }
        Class<?> returnType = elementMock.getElementIdentifier().getElementMethod().getReturnType();
        Class<? extends AbstractWebChildElement> elementImplementationClass = element.getEnvironment()
                .getService(WebPageService.class)
                .getConfiguration()
                .getElementsConfiguration()
                .getElementImplementations()
                .get(returnType);

        WebElementActionRegistry webElementActionRegistry = createWebElementActionRegistryFor(elementImplementationClass);

        Optional<JsOperation<Boolean>> optionalStringJsOperation = webElementActionRegistry.getAction(IS_PRESENT_METHOD, Boolean.class)
                .orElseThrow(() -> new ElementActionNotDeclaredException(ELEMENT_ACTION_NOT_DECLARED.getMessage(IS_PRESENT_METHOD)))
                .getJsOperation(elementMock, PRESENTED);

        if (optionalStringJsOperation.isPresent()) {
            WebLocatorChain locatorChainToListBlock = element.getLocatorChain();
            WebLocatorHolder listLocatorHolder = locatorChainToListBlock.getLastLocator()
                    .setCalculateHash(true)
                    .setExpectedHash(hash);
            WebLocatorHolder listBlockLocatorHolder = element
                    .getLocator(WebComponents.LI)
                    .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(LI))
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson())));
            locatorChainToListBlock.addLocator(listBlockLocatorHolder);
            JsOperation<Boolean> operation = optionalStringJsOperation.get();
            WebLocatorChain locatorChainToTextValues = operation.getLocatorChain();
            locatorChainToTextValues.addLocatorsToTop(locatorChainToListBlock.getAllLocators());
            JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
            operationResult.ifException(exception -> {
                throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
            });
            Map<Integer, Boolean> presentValues = operationResult.multipleResult().getValues();
            String returnedHash = operationResult.getJsWebLocatorProcessingResult(listLocatorHolder.getLocatorId())
                    .orElseThrow(() -> new RuntimeException("Результат обработки локатора не найден"))
                    .getHash()
                    .orElseThrow(() -> new RuntimeException("Хэш у запрашиваемого элемента не рассчитан"));
            return WebFilterResult.of(getMatches(presentValues), returnedHash);
        } else {
            WebFilterResult filterResult = element.filter(emptyListFilter())
                    .setInitialHash(hash)
                    .getResult();
            WebPageFactory webPageFactory = element.getEnvironment().getService(WebPageService.class).getWebPageFactory();
            Map<Integer, WebMappedBlock> webMappedBlocks = webPageFactory.createWebListBlocks(element, filterResult);
            Map<Integer, Boolean> presentValues = new HashMap<>();
            for (Entry<Integer, WebMappedBlock> webMappedBlockEntry : webMappedBlocks.entrySet()) {
                WebChildElement elementToExtractPresentMark = webMappedBlockEntry.getValue()
                        .getElementRegistry()
                        .getElementByMethod(elementMock.getElementIdentifier().getElementMethod())
                        .orElseThrow();
                boolean present = elementToExtractPresentMark.isPresent();
                presentValues.put(webMappedBlockEntry.getKey(), present);
            }
            String returnedHash = filterResult.getHash();
            return WebFilterResult.of(getMatches(presentValues), returnedHash);
        }
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

}
