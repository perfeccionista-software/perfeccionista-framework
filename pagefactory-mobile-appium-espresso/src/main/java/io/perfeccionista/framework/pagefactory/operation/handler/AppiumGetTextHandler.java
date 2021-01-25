package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileGetTextAvailable;
import org.jetbrains.annotations.NotNull;

public class AppiumGetTextHandler implements EndpointHandler<String> {

    private MobileGetTextAvailable element;

    public AppiumGetTextHandler(@NotNull MobileGetTextAvailable element) {
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
