package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.exceptions.attachments.JsonAttachmentEntry;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.options.GetTextOptions;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetTextOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TEXT;

public class WebItemElementTextValueExtractor<T extends WebBlock<?>> implements WebItemValueExtractor<String, T> {

    private final String elementPath;
    private final WebGetTextAvailable elementFrame;

    public WebItemElementTextValueExtractor(@NotNull String elementPath) {
        this.elementPath = elementPath;
        this.elementFrame = null;
    }

    public WebItemElementTextValueExtractor(@NotNull WebGetTextAvailable elementFrame) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebListFilter<T> filter) {
        FilterResult filterResult = filter.getFilterResult();
        Set<Integer> indexes = filterResult.getIndexes();
        String hash = filterResult.getHash();
        WebList<T> element = filter.getElement();

        // Цепочка от корня страницы до WebListBlock
        WebSelectorChain listLocatorChain = element.getSelectorChain()
                .updateLastSelector(locator -> locator.setCalculateHash(true))
                .updateLastSelector(locator -> locator.setExpectedHash(hash))
                .addLastSelector(element.getRequiredSelector(ITEM))
                .updateLastSelector(locator -> locator.setIndexes(indexes));

        // Находим необходимый элемент, заданный по пути или по методу
        WebGetTextAvailable elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getItemFrame().getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebGetTextAvailable.class);
        } else {
            elementToFilter = element.getItemFrame().getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebGetTextAvailable.class);
        }

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetTextOperationType operationType = WebGetTextOperationType.of(elementToFilter, GetTextOptions.getTextForComponent(TEXT));
        WebElementOperation<String> operation = WebElementOperationHandler.of(elementToFilter, operationType, TEXT)
                .getOperation();
        operation.getLocatorChain()
                .addFirstSelectors(listLocatorChain);

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
