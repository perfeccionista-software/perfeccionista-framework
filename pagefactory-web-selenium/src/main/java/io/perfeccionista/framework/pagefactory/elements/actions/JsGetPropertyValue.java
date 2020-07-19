package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyExtractor;
import io.perfeccionista.framework.pagefactory.elements.properties.WebElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class JsGetPropertyValue implements WebElementActionImplementation<String> {

    @Override
    public @Nullable String execute(WebChildElement element, Object... args) {
        WebElementPropertyHolder elementPropertyHolder = (WebElementPropertyHolder) args[0];
        WebElementPropertyExtractor<WebChildElement> elementPropertyExtractor = elementPropertyHolder.getPropertyExtractor();
        return elementPropertyExtractor.extract(element, elementPropertyHolder.getLocatorHolder());
    }

    @Override
    public Optional<JsOperation<String>> getJsOperation(WebChildElement element, Object... args) {
        return Optional.empty();
    }

}
