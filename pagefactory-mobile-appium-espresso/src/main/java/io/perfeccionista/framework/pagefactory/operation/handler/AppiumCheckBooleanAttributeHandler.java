package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.android.AndroidElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AppiumCheckBooleanAttributeHandler implements EndpointHandler<Boolean> {

    private final MobileChildElement element;
    private final String attributeName;
    private final boolean expectedValue;

    public AppiumCheckBooleanAttributeHandler(@NotNull MobileChildElement element, @NotNull String attributeName, boolean expectedValue) {
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
        if (Objects.isNull(attributeValue)) {
            return false;
        }
        // TODO: Если при парсинге ошибка, то выбрасывать ошибку о том, что аттрибут не булеан.
        return Boolean.parseBoolean(attributeValue) == expectedValue;
    }

}
