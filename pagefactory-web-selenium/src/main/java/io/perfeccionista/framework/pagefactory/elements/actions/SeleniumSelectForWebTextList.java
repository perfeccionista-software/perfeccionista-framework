package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.attachment.JsonAttachmentEntry;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterResult;
import org.openqa.selenium.WebElement;

public class SeleniumSelectForWebTextList implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        // Check this type
        WebTextListFilter filter = (WebTextListFilter) args[0];
        WebElement webElement = filter.filter((WebTextList) element).extractOne(new WebTextListBlockWebElementExtractor()).get();
        element.getWebBrowserDispatcher().getExceptionMapper()
                .map(webElement::click, element.getElementIdentifier().getLastUsedName())
                .ifException(exception -> {
                    throw exception.addAttachmentEntry(JsonAttachmentEntry.of("Element", element.toJson()));
                });
        return null;
    }

    private class WebTextListBlockWebElementExtractor implements WebTextListBlockValueExtractor<WebElement> {

        @Override
        public MultipleResult<WebElement> extractValues(WebTextList element, WebTextListFilterResult filter) {

            return null;
        }

    }

}
