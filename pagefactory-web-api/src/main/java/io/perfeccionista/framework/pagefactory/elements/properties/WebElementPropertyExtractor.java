package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public interface WebElementPropertyExtractor<T extends WebChildElement> {

    String extract(@NotNull T element, Optional<WebLocatorHolder> locatorHolder);

    JsOperation<String> getJsOperation(@NotNull WebChildElement element, Optional<WebLocatorHolder> locatorHolder);

}
