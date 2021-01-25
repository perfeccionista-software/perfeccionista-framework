package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import org.jetbrains.annotations.NotNull;

public class AppiumGetColorHandler implements EndpointHandler<Color> {

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public Color handle(Object endpoint) {
        return null;
    }
}
