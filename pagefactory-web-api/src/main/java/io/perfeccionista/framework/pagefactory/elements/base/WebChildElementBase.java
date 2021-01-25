package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface WebChildElementBase extends WebElementBase, WebParentHolderAvailable {

    @NotNull WebLocatorHolder getRequiredLocator(@NotNull String componentName);

    Optional<WebLocatorHolder> getOptionalLocator(@NotNull String componentName);

    @NotNull <R> Class<? extends EndpointHandler<R>> getEndpointHandler(@NotNull String actionName, @NotNull Class<R> returnType);

    WebChildElementBase executeAction(@NotNull String name, Object... args);

    @NotNull WebElementIdentifier getElementIdentifier();

}
