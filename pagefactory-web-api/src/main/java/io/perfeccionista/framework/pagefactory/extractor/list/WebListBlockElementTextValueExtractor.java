package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetTextOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;

public class WebListBlockElementTextValueExtractor implements WebListBlockValueExtractor<String> {

    private final String elementPath;
    private final WebGetTextAvailable elementFrame;

    public WebListBlockElementTextValueExtractor(@NotNull String elementPath) {
        this.elementPath = elementPath;
        this.elementFrame = null;
    }

    public WebListBlockElementTextValueExtractor(@NotNull WebGetTextAvailable elementFrame) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebListFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
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
        WebGetTextAvailable elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getWebListFrame().getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebGetTextAvailable.class);
        } else {
            elementToFilter = element.getWebListFrame().getMappedBlockFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebGetTextAvailable.class);
        }

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetTextOperationType operationType = WebGetTextOperationType.of(elementToFilter);
        WebElementOperation<String> operation = WebElementOperationHandler.of(elementToFilter, operationType, TEXT)
                .getOperation();
        operation.getLocatorChain()
                .addFirstLocators(listLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<String> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element))
                            .addLastAttachmentEntry(JsonAttachmentEntry.of("Operation", operation.toJson()));
                });

        return operationResult.getResults();
    }

}
