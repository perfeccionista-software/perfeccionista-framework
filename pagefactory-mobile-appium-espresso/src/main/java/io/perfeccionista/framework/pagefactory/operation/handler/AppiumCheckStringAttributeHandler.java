package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.android.AndroidElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;

public class AppiumCheckStringAttributeHandler implements EndpointHandler<Boolean> {

    private final MobileChildElement element;
    private final String attributeName;
    private final StringValue expectedValue;

    public AppiumCheckStringAttributeHandler(@NotNull MobileChildElement element, @NotNull String attributeName, @NotNull StringValue expectedValue) {
        this.element = element;
        this.attributeName = attributeName;
        this.expectedValue = expectedValue;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public Boolean handle(Object endpoint) {
        String attributeValue = ((AndroidElement) endpoint).getAttribute(attributeName);
        return expectedValue.check(attributeValue);
    }

}
