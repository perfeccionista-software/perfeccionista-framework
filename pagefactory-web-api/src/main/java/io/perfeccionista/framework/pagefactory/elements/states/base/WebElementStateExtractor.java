package io.perfeccionista.framework.pagefactory.elements.states.base;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface WebElementStateExtractor {

    WebElementOperation<Boolean> getOperation(@NotNull WebChildElementBase element, Optional<WebLocatorHolder> locatorHolder);

}
