package io.perfeccionista.framework.pagefactory.elements.states.base;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface WebElementStateExtractor {

    WebElementOperation<Boolean> getOperation(@NotNull WebChildElement element, Optional<WebSelectorHolder> locatorHolder);

}
