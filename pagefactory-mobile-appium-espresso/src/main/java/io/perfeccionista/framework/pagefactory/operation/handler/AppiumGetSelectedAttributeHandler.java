package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.android.AndroidElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetLabelAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

public class AppiumGetSelectedAttributeHandler implements EndpointHandler<Boolean> {

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public Boolean handle(Object endpoint) {
        return Boolean.valueOf(((AndroidElement)endpoint).getAttribute("selected"));
    }
}
