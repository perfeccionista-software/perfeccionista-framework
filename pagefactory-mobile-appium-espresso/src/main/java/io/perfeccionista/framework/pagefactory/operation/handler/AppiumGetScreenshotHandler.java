package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

public class AppiumGetScreenshotHandler implements EndpointHandler<Screenshot> {

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public Screenshot handle(Object endpoint) {
        return null;
    }
}
