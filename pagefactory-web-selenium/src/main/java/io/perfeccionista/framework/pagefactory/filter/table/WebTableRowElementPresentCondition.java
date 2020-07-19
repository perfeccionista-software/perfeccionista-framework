package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.ElementActionNotDeclaredException;
import io.perfeccionista.framework.exceptions.ElementNotDeclaredException;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;
import io.perfeccionista.framework.exceptions.TableColumnLocatorNotDeclaredException;
import io.perfeccionista.framework.exceptions.TableColumnNotDeclaredException;
import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionRegistry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.components.WebComponents;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.mapping.TableColumnHolder;
import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilters;
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
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.TABLE_COLUMN_LOCATOR_NOT_DECLARED;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.TABLE_COLUMN_NOT_DECLARED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.PRESENTED;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.IS_PRESENT_METHOD;
import static io.perfeccionista.framework.pagefactory.factory.handlers.WebElementActionAnnotationHandler.createWebElementActionRegistryFor;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.AND;
import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.OR;
import static io.perfeccionista.framework.pagefactory.filter.WebFilters.emptyTableFilter;
import static io.perfeccionista.framework.utils.ReflectionUtils.readField;

public class WebTableRowElementPresentCondition implements WebTableRowCondition {

    private final Deque<WebTableRowConditionHolder> childConditions = new ArrayDeque<>();

    private final String columnName;
    private final String elementName;
    private WebChildElement elementMock;

    private boolean inverse = false;

    public WebTableRowElementPresentCondition(String columnName, IsPresentAvailable elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = (WebChildElement) elementMock;
    }

    public WebTableRowElementPresentCondition(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
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
    public WebTableRowElementPresentCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebTableRowConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebTable element, @Nullable String hash) {
        // TODO: Переписать эту логику!!! Она работает, но тут много лишнего.
        Map<String, TableColumnHolder> tableColumnHolders = readField("tableColumnHolders", element);
        TableColumnHolder tableColumnHolder = Optional.ofNullable(tableColumnHolders.get(columnName))
                .orElseThrow(() -> new TableColumnNotDeclaredException(
                        TABLE_COLUMN_NOT_DECLARED.getMessage(columnName, element.getElementIdentifier().getLastUsedName())));
        // TODO: Тут лучше создавать новый мок из пейджфактори с энвайроментом
        if (elementMock == null) {
            // TODO: Можно все-таки хранить внутри холдера не класс, а мок и внутри мока хранить класс для пейджфактори
            Class<? extends WebMappedBlock> mappedBlockClass = tableColumnHolder.getBodyClass();
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
            WebLocatorChain locatorChainToTableCell = element.getLocatorChain();
            WebLocatorHolder tableLocatorHolder = locatorChainToTableCell.getLastLocator()
                    .setCalculateHash(true)
                    .setExpectedHash(hash);
            WebLocatorHolder tableRowLocatorHolder = element
                    .getLocator(WebComponents.TBODY_ROW)
                    .orElseThrow(() -> new LocatorNotDeclaredException(ELEMENT_LOCATOR_NOT_DECLARED.getMessage(TBODY_ROW))
                            .addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson())));
            locatorChainToTableCell.addLocator(tableRowLocatorHolder);
            WebLocatorHolder tableColumnLocator = tableColumnHolder.getBodyLocator()
                    .orElseThrow(() -> new TableColumnLocatorNotDeclaredException(
                            TABLE_COLUMN_LOCATOR_NOT_DECLARED.getMessage(columnName, element.getElementIdentifier().getLastUsedName())));
            locatorChainToTableCell.addLocator(tableColumnLocator);
            JsOperation<Boolean> operation = optionalStringJsOperation.get();
            WebLocatorChain locatorChainToTextValues = operation.getLocatorChain();
            locatorChainToTextValues.addLocatorsToTop(locatorChainToTableCell.getAllLocators());
            JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor().executeOperation(operation);
            operationResult.ifException(exception -> {
                throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
            });
            Map<Integer, Boolean> presentValues = operationResult.multipleResult().getValues();
            String returnedHash = operationResult.getJsWebLocatorProcessingResult(tableLocatorHolder.getLocatorId())
                    .orElseThrow(() -> new RuntimeException("Результат обработки локатора не найден"))
                    .getHash()
                    .orElseThrow(() -> new RuntimeException("Хэш у запрашиваемого элемента не рассчитан"));
            return WebFilterResult.of(getMatches(presentValues), returnedHash);
        } else {
            WebFilterResult filterResult = element.filter(emptyTableFilter())
                    .setInitialHash(hash)
                    .getResult();
            WebPageFactory webPageFactory = element.getEnvironment().getService(WebPageService.class).getWebPageFactory();
            Map<Integer, WebMappedBlock> webMappedBlocks = webPageFactory.createWebTableCells(element, columnName, filterResult);
            // В зависимости от того, что указано при создании достаем нужные элементы или по имени или по цепочке методов.
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