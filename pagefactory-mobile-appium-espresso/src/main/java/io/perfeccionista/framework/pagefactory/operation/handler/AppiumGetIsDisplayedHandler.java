package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.android.AndroidElement;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsDisplayedAvailable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AppiumGetIsDisplayedHandler implements EndpointHandler<Boolean> {

    private final MobileIsDisplayedAvailable element;

    public AppiumGetIsDisplayedHandler(@NotNull MobileIsDisplayedAvailable element) {
        this.element = element;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public Boolean handle(Object endpoint) {
        if (Objects.isNull(endpoint)) {
            return false;
        }
        return ((AndroidElement) endpoint).isDisplayed();
    }

}
