package io.perfeccionista.framework.pagefactory.elements.properties.base;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public interface WebElementPropertyExtractor {

    WebElementOperation<String> getOperation(@NotNull WebChildElementBase element, Optional<WebLocatorHolder> locatorHolder);

}
