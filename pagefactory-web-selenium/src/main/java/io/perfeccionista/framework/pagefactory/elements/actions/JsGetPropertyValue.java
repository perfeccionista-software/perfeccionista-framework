package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;

public class JsGetPropertyValue implements WebElementActionImplementation<String> {

    @Override
    public String execute(WebChildElement element, Object... args) {
        WebElementPropertyHolder elementPropertyHolder = (WebElementPropertyHolder) args[0];
        WebElementPropertyExtractor<WebChildElement> elementPropertyExtractor = elementPropertyHolder.getPropertyExtractor();
        return elementPropertyExtractor.extract(element, elementPropertyHolder.getLocatorHolder());
    }

}
