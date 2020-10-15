package io.perfeccionista.framework.pagefactory.elements.properties.base;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public interface WebElementPropertyExtractor {

    JsOperation<String> getJsOperation(@NotNull WebChildElementBase element, Optional<WebLocatorHolder> locatorHolder);

}
