package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;
import org.junit.platform.commons.util.ReflectionUtils;

public class JsGetPropertyValue implements WebElementMethodImplementation<String> {

    @Override
    public OperationResult<String> execute(WebChildElement element, Object... args) {
        WebElementPropertyHolder elementPropertyHolder = (WebElementPropertyHolder) args[0];
        return OperationResult.of(() -> {
            ElementPropertyExtractor<WebChildElement> elementPropertyExtractor = ReflectionUtils.newInstance(elementPropertyHolder.getPropertyExtractor());
            return elementPropertyExtractor.extract(element, elementPropertyHolder.getLocatorHolder());
        });
    }

}
