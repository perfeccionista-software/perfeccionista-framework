package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class SeleniumSelectForWebTextList implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        // Check this type
        WebTextListFilterBuilder filter = (WebTextListFilterBuilder) args[0];
        WebElement webElement = filter
                .build((WebTextList) element)
                .extractOne(new WebTextListBlockWebElementExtractor()).get();
        element.getWebBrowserDispatcher().getExceptionMapper()
                .map(webElement::click, element.getElementIdentifier().getLastUsedName())
                .ifException(exception -> {
                    throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
                });
        return null;
    }

    @Override
    public Optional<JsOperation<Void>> getJsOperation(WebChildElement element, Object... args) {
        return Optional.empty();
    }

    // TODO: Implement
    private class WebTextListBlockWebElementExtractor implements WebTextListBlockValueExtractor<WebElement> {

        @Override
        public MultipleResult<WebElement> extractValues(WebTextList element, WebTextListFilter filter) {

            return null;
        }

    }

}
