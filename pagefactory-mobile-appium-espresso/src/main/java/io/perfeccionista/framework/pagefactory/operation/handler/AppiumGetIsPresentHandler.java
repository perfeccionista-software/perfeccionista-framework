package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsDisplayedAvailable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AppiumGetIsPresentHandler implements EndpointHandler<Boolean> {

    private final MobileIsDisplayedAvailable element;

    public AppiumGetIsPresentHandler(@NotNull MobileIsDisplayedAvailable element) {
        this.element = element;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public Boolean handle(Object endpoint) {
        return Objects.nonNull(endpoint);
    }

}
