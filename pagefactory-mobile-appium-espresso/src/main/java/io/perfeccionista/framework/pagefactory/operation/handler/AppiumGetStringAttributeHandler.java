package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.android.AndroidElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

public class AppiumGetStringAttributeHandler implements EndpointHandler<String> {

    private final MobileChildElement element;
    private final String attributeName;

    public AppiumGetStringAttributeHandler(@NotNull MobileChildElement element, @NotNull String attributeName) {
        this.element = element;
        this.attributeName = attributeName;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public String handle(Object endpoint) {
        return ((AndroidElement) endpoint).getAttribute(attributeName);
    }

}
