package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import io.perfeccionista.framework.exceptions.ElementNotPresent;
import io.perfeccionista.framework.exceptions.SingleResultCreating;
import io.perfeccionista.framework.invocation.timeouts.TimeoutsService;
import io.perfeccionista.framework.invocation.timeouts.type.RepeatInvocationTimeout;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilterBuilder;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Rectangle;

import java.time.Duration;
import java.util.Objects;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_PRESENT;
import static io.perfeccionista.framework.measurements.VerticalDirection.UP;
import static io.perfeccionista.framework.pagefactory.extractor.MobileExtractors.block;

public class AppiumAndroidListScrollToVerticallyHandler implements EndpointHandler<Void> {

    private MobileList element;
    private VerticalDirection scrollDirection;
    private MobileListFilterBuilder filterBuilder;

    public AppiumAndroidListScrollToVerticallyHandler(@NotNull MobileList element,
                                                      @NotNull VerticalDirection scrollDirection,
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
        Rectangle rootElementSize = androidElement.getRect();

        int scrollOffset = Double.valueOf(element.getElementBounds().getDimensions().getHeight() / 2)
                .intValue();

//        int xOffset = rootElementSize.x + rootElementSize.getWidth() / 2;
        int xOffset = rootElementSize.x + 1;
        if (Objects.isNull(getResult())) {
            TouchAction touchAction = new TouchAction(driverInstance);
            if (scrollDirection == UP) {
                touchAction.press(PointOption.point(xOffset, rootElementSize.y))
                        .moveTo(PointOption.point(xOffset,  rootElementSize.y + scrollOffset))
                        .release()
                        .perform();
            } else {
                touchAction.press(PointOption.point(xOffset, rootElementSize.y + scrollOffset))
                        .moveTo(PointOption.point(xOffset, rootElementSize.y))
                        .release()
                        .perform();
            }
            MobileBlock result = getResult();
            if (Objects.nonNull(result)) {
                result.getPropertyValue("visible");
                return null;
            }
            // TODO: В сообщение нужно добавить информацию про фильтр
            throw ElementNotPresent.exception(ELEMENT_NOT_PRESENT.getMessage(element.getElementIdentifier().getLastUsedName()))
                    .setProcessed(true);
        }
        return null;
    }

    protected MobileBlock getResult() {
        Duration current = null;
        try {
            current = element.getEnvironment().getService(TimeoutsService.class).getTimeout(RepeatInvocationTimeout.class);
            element.getEnvironment().getService(TimeoutsService.class).setTimeout(RepeatInvocationTimeout.class, Duration.ofNanos(1));
            return filterBuilder.build(element)
                    .extractOne(block())
                    .getResult();
        } catch (SingleResultCreating.SingleResultCreatingException exception) {
            return null;
        } finally {
            if (current != null) {
                element.getEnvironment().getService(TimeoutsService.class).setTimeout(RepeatInvocationTimeout.class, current);
            }
        }
    }

}
