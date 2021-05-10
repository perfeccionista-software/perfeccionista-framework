package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import org.jetbrains.annotations.NotNull;

public class AppiumAndroidParentScrollToHorizontallyHandler implements EndpointHandler<Void> {

    private MobileParentElement element;
    private HorizontalDirection scrollDirection;
    private MobileChildElement scrollToElement;

    public AppiumAndroidParentScrollToHorizontallyHandler(@NotNull MobileParentElement element,
                                                          @NotNull HorizontalDirection scrollDirection,
                                                          @NotNull MobileChildElement scrollToElement) {
        this.element = element;
        this.scrollDirection = scrollDirection;
        this.scrollToElement = scrollToElement;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public Void handle(Object endpoint) {
        AndroidElement androidElement = (AndroidElement) endpoint;
        AndroidDriver instance = element.getMobileDeviceDispatcher().getInstance(AndroidDriver.class);
        TouchAction touchAction = new TouchAction(instance);
        // Вычисляем координаты элемента
        touchAction.press(PointOption.point(300, 100))
                .moveTo(PointOption.point(300, 500))
                .release()
                .perform();
        return null;
    }

}
