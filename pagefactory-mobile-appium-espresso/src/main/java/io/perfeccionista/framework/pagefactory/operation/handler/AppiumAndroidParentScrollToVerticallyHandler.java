package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import io.perfeccionista.framework.exceptions.ElementNotPresent;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.MobileParentElement;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Rectangle;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_PRESENT;
import static io.perfeccionista.framework.measurements.VerticalDirection.UP;

public class AppiumAndroidParentScrollToVerticallyHandler implements EndpointHandler<Void> {

    private MobileParentElement element;
    private VerticalDirection scrollDirection;
    private MobileChildElement scrollToElement;

    public AppiumAndroidParentScrollToVerticallyHandler(@NotNull MobileParentElement element,
                                                        @NotNull VerticalDirection scrollDirection,
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
        AndroidDriver driverInstance = element.getMobileDeviceDispatcher()
                .getInstance(AndroidDriver.class);
        AndroidElement androidElement = (AndroidElement) endpoint;
        Rectangle rootElementSize = androidElement.getRect();
        int scrollOffset = rootElementSize.getHeight() / 2;
        if (!scrollToElement.isDisplayed()) {
            TouchAction touchAction = new TouchAction(driverInstance);
            if (scrollDirection == UP) {
                touchAction.press(PointOption.point(1, 100))
                        .moveTo(PointOption.point(1, scrollOffset + 100))
                        .release()
                        .perform();
            } else {
                touchAction.press(PointOption.point(1, scrollOffset + 100))
                        .moveTo(PointOption.point(1, 100))
                        .release()
                        .perform();
            }
            if (scrollToElement.isDisplayed()) {
                scrollToElement.getPropertyValue("contentSize");
                return null;
            }
            throw ElementNotPresent.exception(ELEMENT_NOT_PRESENT.getMessage(scrollToElement.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true);
        }
        return null;
    }

}
