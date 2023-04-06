package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

public interface WebParentElement extends WebElementBase {

    WebElementRegistry getElementRegistry();

    @NotNull <R> Class<? extends EndpointHandler<R>> getEndpointHandler(@NotNull String actionName, @NotNull Class<R> returnType);

//    WebParentElement scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull WebChildElement childElement);
//
//    WebParentElement scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull WebChildElement childElement);

}
