package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import io.perfeccionista.framework.pagefactory.operation.JsOperationResult;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

public class WebTextListBlockTextValueExtractor implements WebTextListBlockValueExtractor<String> {

    @Override
    public Map<Integer, String> extractValues(@NotNull WebTextListFilter filter) {
        WebFilterResult filterResult = filter.getFilterResult();
        Set<Integer> indexes = filterResult.getIndexes();
        String hash = filterResult.getHash();
        WebTextList element = filter.getElement();
        WebLink elementToFilter = element.getWebTextListFrame().getMappedBlockFrame().textLink();

        // Цепочка от корня страницы до WebListBlock
        WebLocatorChain listLocatorChain = element.getLocatorChain()
                .updateLastLocator(locator -> locator.setCalculateHash(true))
                .updateLastLocator(locator -> locator.setExpectedHash(hash))
                .addLastLocator(element.getRequiredLocator(LI))
                .updateLastLocator(locator -> locator.setIndexes(indexes));

        // Добавляем в цепочку локаторов операции локаторы до блока WebListBlock
        JsOperation<String> jsOperation = elementToFilter
                .getJsOperationActionImplementation(GET_TEXT_METHOD, String.class)
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
