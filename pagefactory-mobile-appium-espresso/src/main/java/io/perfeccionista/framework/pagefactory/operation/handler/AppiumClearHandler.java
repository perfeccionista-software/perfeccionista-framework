package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.android.AndroidElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsDisplayedAvailable;
import org.jetbrains.annotations.NotNull;

public class AppiumClearHandler implements EndpointHandler<Void> {

    private final MobileIsDisplayedAvailable element;

    public AppiumClearHandler(@NotNull MobileIsDisplayedAvailable element) {
        this.element = element;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public Void handle(Object endpoint) {
        ((AndroidElement) endpoint).clear();
        return null;
    }

}
