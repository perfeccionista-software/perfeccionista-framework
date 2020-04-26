package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsGetPropertyValue implements WebElementActionImplementation<String> {

    @Override
    public String execute(WebChildElement element, Object... args) {
        WebElementPropertyHolder elementPropertyHolder = (WebElementPropertyHolder) args[0];
        ElementPropertyExtractor<WebChildElement> elementPropertyExtractor = ReflectionUtils.newInstance(elementPropertyHolder.getPropertyExtractor());
        return elementPropertyExtractor.extract(element, elementPropertyHolder.getLocatorHolder());
    }

}
