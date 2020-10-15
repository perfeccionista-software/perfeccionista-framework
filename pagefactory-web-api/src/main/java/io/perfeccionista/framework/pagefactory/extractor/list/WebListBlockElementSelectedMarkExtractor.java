package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.IS_SELECTED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

public class WebListBlockElementSelectedMarkExtractor implements WebListBlockValueExtractor<Boolean> {

    private final String elementPath;
    private final WebChildElement elementFrame;

    public WebListBlockElementSelectedMarkExtractor(@NotNull String elementPath) {
        this.elementPath = elementPath;
        this.elementFrame = null;
    }

    public WebListBlockElementSelectedMarkExtractor(@NotNull IsSelectedAvailable elementFrame) {
        this.elementPath = null;
        this.elementFrame = (WebChildElement) elementFrame;
    }

    @Override
    public Map<Integer, Boolean> extractValues(@NotNull WebListFilter filter) {
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

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        JsOperation<Boolean> jsOperation = elementToFilter
                .getJsOperationActionImplementation(IS_SELECTED_METHOD, Boolean.class)
                .getJsOperation(elementToFilter);
        jsOperation.getLocatorChain()
                .addFirstLocators(listLocatorChain);

        // Выполняем операцию
        JsOperationResult<Boolean> operationResult = element.getWebBrowserDispatcher().executor()
                .executeOperation(jsOperation)
                .ifException(exception -> {
                    throw exception.addLastAttachmentEntry(WebElementAttachmentEntry.of(element));
                });

        return operationResult.getResults();
    }

}
