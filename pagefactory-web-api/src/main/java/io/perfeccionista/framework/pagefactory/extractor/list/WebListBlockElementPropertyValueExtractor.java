package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.exceptions.WebElementPropertyNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_NOT_FOUND;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

public class WebListBlockElementPropertyValueExtractor implements WebListBlockValueExtractor<String> {

    private final String elementPath;
    private final WebChildElement elementFrame;
    private final String propertyName;

    public WebListBlockElementPropertyValueExtractor(@NotNull String elementPath, @NotNull String propertyName) {
        this.elementPath = elementPath;
        this.elementFrame = null;
        this.propertyName = propertyName;
    }

    public WebListBlockElementPropertyValueExtractor(@NotNull WebChildElement elementFrame, @NotNull String propertyName) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
        this.propertyName = propertyName;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebListFilter filter) {
        WebFilterResult filterResult = filter.getFilterResult();
        Set<Integer> indexes = filterResult.getIndexes();
        String hash = filterResult.getHash();
        WebList element = filter.getElement();

        // Цепочка от корня страницы до WebListBlock
        WebLocatorChain listLocatorChain = element.getLocatorChain()
                .updateLastLocator(locator -> locator.setCalculateHash(true))
                .updateLastLocator(locator -> locator.setExpectedHash(hash))
                .addLastLocator(element.getRequiredLocator(LI))
                .updateLastLocator(locator -> locator.setIndexes(indexes));

        // Находим необходимый элемент, заданный по пути или по методу
        WebChildElement elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getWebListFrame().getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebChildElement.class);
        } else {
            elementToFilter = element.getWebListFrame().getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod());
        }

        // Формируем операцию по извлечению значения свойства
        JsOperation<String> jsOperation = elementToFilter
                .getProperty(propertyName)
                .orElseThrow(() -> WebElementPropertyNotFound.exception(ELEMENT_PROPERTY_NOT_FOUND.getMessage(propertyName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(elementToFilter)))
                .getJsOperation(elementToFilter);
        jsOperation.getLocatorChain()
                .addFirstLocators(listLocatorChain);

        // Выполняем операцию
        JsOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(jsOperation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        return operationResult.getResults();
    }

}
