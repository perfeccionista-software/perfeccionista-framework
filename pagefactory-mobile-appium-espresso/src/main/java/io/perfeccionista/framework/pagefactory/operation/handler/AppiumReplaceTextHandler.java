package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.remote.RemoteWebElement;

public class AppiumReplaceTextHandler implements EndpointHandler<Void> {

    private final MobileChildElementBase element;
    private final String valueToInput;

    public AppiumReplaceTextHandler(@NotNull MobileChildElementBase element, @NotNull String valueToInput) {
        this.element = element;
        this.valueToInput = valueToInput;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public Void handle(Object endpoint) {
        RemoteWebElement webElement = (RemoteWebElement) endpoint;
        webElement.clear();
        webElement.sendKeys(valueToInput);
        return null;
    }

}
