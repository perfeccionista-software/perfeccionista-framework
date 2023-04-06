package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.exceptions.base.PerfeccionistaRuntimeException;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationHandler;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.type.WebGetIsEnabledOperationType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ENABLED;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.ITEM;

public class WebItemElementEnabledMarkExtractor<T extends WebBlock<?>> implements WebItemValueExtractor<Boolean, T> {

    private final String elementPath;
    private final WebIsEnabledAvailable elementFrame;

    public WebItemElementEnabledMarkExtractor(@NotNull String elementPath) {
        this.elementPath = elementPath;
        this.elementFrame = null;
    }

    public WebItemElementEnabledMarkExtractor(@NotNull WebIsEnabledAvailable elementFrame) {
        this.elementPath = null;
        this.elementFrame = elementFrame;
    }

    @Override
    public Map<Integer, Boolean> extractValues(@NotNull WebListFilter<T> filter) {
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
        WebIsEnabledAvailable elementToFilter;
        if (elementPath != null) {
            elementToFilter = element.getItemFrame().getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByPath(elementPath, WebIsEnabledAvailable.class);
        } else {
            elementToFilter = element.getItemFrame().getMappedItemFrame()
                    .getElementRegistry()
                    .getRequiredElementByMethod(elementFrame.getElementIdentifier().getElementMethod(), WebIsEnabledAvailable.class);
        }

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        WebGetIsEnabledOperationType operationType = WebGetIsEnabledOperationType.of(elementToFilter);
        WebElementOperation<Boolean> operation = WebElementOperationHandler.of(elementToFilter, operationType, ENABLED)
                .getOperation();
        operation.getLocatorChain()
                .addFirstSelectors(listLocatorChain);

        // Выполняем операцию
        WebElementOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeWebElementOperation(operation)
                .ifException((exceptionMapper, originalException) -> {
                    PerfeccionistaRuntimeException exception = exceptionMapper.mapElementException(element, originalException);
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        return operationResult.getResults();
    }

}
