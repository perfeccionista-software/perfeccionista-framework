package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import org.openqa.selenium.WebElement;

public class SeleniumSelectForWebTextList implements WebElementActionImplementation<Void> {

    @Override
    public Void execute(WebChildElement element, Object... args) {
        // Check this type
        WebTextListFilter filter = (WebTextListFilter) args[0];
        WebElement webElement = filter.filter((WebTextList) element).extractOne(new WebTextListBlockWebElementExtractor()).get();
        element.getWebBrowserDispatcher().getExceptionMapper(SeleniumExceptionMapper.class).map(webElement::click);
        return null;
    }

    private class WebTextListBlockWebElementExtractor implements WebTextListBlockValueExtractor<WebElement> {

        @Override
        public MultipleResult<WebElement> extractValues(WebTextList element, WebTextListFilter filter) {

            return null;
        }

    }

}
