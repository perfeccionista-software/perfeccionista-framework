package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileTapAvailable;
import org.jetbrains.annotations.NotNull;

public class AppiumAndroidTapHandler implements EndpointHandler<Void> {

    private MobileTapAvailable element;

    public AppiumAndroidTapHandler(@NotNull MobileTapAvailable element) {
        this.element = element;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public Void handle(Object endpoint) {
        AndroidElement androidElement = (AndroidElement) endpoint;
        AndroidDriver instance = element.getMobileDeviceDispatcher().getInstance(AndroidDriver.class);
        TouchAction action = new TouchAction(instance);
        action.tap(TapOptions.tapOptions().withPosition(PointOption.point(androidElement.getCenter())));
        action.perform();
        return null;
    }

}
