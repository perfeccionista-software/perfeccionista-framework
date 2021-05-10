package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

public interface MobileParentElement extends MobileElementBase {

    MobileElementRegistry getElementRegistry();

    @NotNull <R> Class<? extends EndpointHandler<R>> getEndpointHandler(@NotNull String actionName, @NotNull Class<R> returnType);

//    MobileParentElement scrollToHorizontally(@NotNull HorizontalDirection scrollDirection, @NotNull MobileChildElement childElement);
//
//    MobileParentElement scrollToVertically(@NotNull VerticalDirection scrollDirection, @NotNull MobileChildElement childElement);

}
