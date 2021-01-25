package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetLabelAvailable;
import org.jetbrains.annotations.NotNull;

public class AppiumGetLabelHandler implements EndpointHandler<String> {
    private MobileGetLabelAvailable element;

    public AppiumGetLabelHandler(@NotNull MobileGetLabelAvailable element) {
        this.element = element;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public String handle(Object endpoint) {
        MobileElement mobileElement = (MobileElement) endpoint;
        return mobileElement.getText();
    }
}

