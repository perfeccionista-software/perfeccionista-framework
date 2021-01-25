package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import io.perfeccionista.framework.exceptions.ElementNotPresent;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_PRESENT;
import static io.perfeccionista.framework.measurements.HorizontalDirection.LEFT;
import static io.perfeccionista.framework.pagefactory.extractor.MobileExtractors.block;

public class AppiumAndroidListScrollToHorizontallyHandler implements EndpointHandler<Void> {

    private MobileList element;
    private HorizontalDirection scrollDirection;
    private MobileListFilterBuilder filterBuilder;

    public AppiumAndroidListScrollToHorizontallyHandler(@NotNull MobileList element,
                                                        @NotNull HorizontalDirection scrollDirection,
                                                        @NotNull MobileListFilterBuilder filterBuilder) {
        this.element = element;
        this.scrollDirection = scrollDirection;
        this.filterBuilder = filterBuilder;
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
        ElementBounds elementBounds = element.getElementBounds();

        int scrollOffset = Double.valueOf(elementBounds.getDimensions().getWidth() - 100d)
                .intValue();

        int yCenter = Double.valueOf(elementBounds.getCenter().getY()).intValue();

        if (!hasResult()) {
            TouchAction touchAction = new TouchAction(driverInstance);
            if (scrollDirection == LEFT) {
                touchAction.press(PointOption.point(1, yCenter))
                        .moveTo(PointOption.point(scrollOffset, yCenter))
                        .release()
                        .perform();
            } else {
                touchAction.press(PointOption.point(scrollOffset, yCenter))
                        .moveTo(PointOption.point(1, yCenter))
                        .release()
                        .perform();
            }
            if (hasResult()) {
                MobileBlock result = filterBuilder.build(element)
                        .extractOne(block())
                        .getResult();
                result.getPropertyValue("contentSize");
                return null;
            }
            // TODO: В сообщение нужно добавить информацию про фильтр
            throw ElementNotPresent.exception(ELEMENT_NOT_PRESENT.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true);
        }
        return null;
    }

    protected boolean hasResult() {
        return filterBuilder.build(element)
                .extractAll(block())
                .getSize() > 0;
    }

}
