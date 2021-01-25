package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.name.MobileElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface MobileChildElementBase extends MobileElementBase, MobileParentHolderAvailable {

    @NotNull MobileLocatorHolder getRequiredLocator(@NotNull String componentName);

    Optional<MobileLocatorHolder> getOptionalLocator(@NotNull String componentName);

    @NotNull <R> Class<? extends EndpointHandler<R>> getEndpointHandler(@NotNull String actionName, @NotNull Class<R> returnType);

    MobileChildElementBase executeAction(@NotNull String name, Object... args);

    @NotNull MobileElementIdentifier getElementIdentifier();

}
