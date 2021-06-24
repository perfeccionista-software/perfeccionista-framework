package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetStringAttributeValueOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LI;

public class WebListBlockElementPropertyValueExtractor<T extends WebBlock> implements WebListBlockValueExtractor<String, T> {

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
    public Map<Integer, String> extractValues(@NotNull WebListFilter<T> filter) {
        FilterResult filterResult = filter.getFilterResult();
        Set<Integer> indexes = filterResult.getIndexes();
        String hash = filterResult.getHash();
        WebList<T> element = filter.getElement();

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
        Optional<WebElementPropertyHolder> optionalPropertyHolder = elementToFilter.getProperty(propertyName);
        WebElementOperation<String> operation;
        if (optionalPropertyHolder.isPresent()) {
            WebElementPropertyHolder propertyHolder = optionalPropertyHolder.get();
            operation = propertyHolder.getOperation(elementToFilter);
        } else {
            WebGetStringAttributeValueOperationType operationType = WebGetStringAttributeValueOperationType.of(elementToFilter, propertyName);
            operation = WebElementOperationHandler.of(elementToFilter, operationType, propertyName).getOperation();
        }
        operation.getLocatorChain()
                .addFirstLocators(listLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        return operationResult.getResults();
    }

}
