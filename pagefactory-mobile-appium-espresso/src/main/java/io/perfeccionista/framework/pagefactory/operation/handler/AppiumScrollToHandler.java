package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.perfeccionista.framework.exceptions.ElementNotOnTheScreen;
import io.perfeccionista.framework.exceptions.ResponseFormatIsNotValid;
import io.perfeccionista.framework.exceptions.attachments.TextAttachmentEntry;
import io.perfeccionista.framework.invocation.runner.InvocationName;
import io.perfeccionista.framework.measurements.Direction;
import io.perfeccionista.framework.measurements.HorizontalDirection;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.measurements.VerticalDirection;
import io.perfeccionista.framework.pagefactory.dispatcher.driver.AndroidEspressoDriver;
import io.perfeccionista.framework.pagefactory.dispatcher.screen.MobileScreenBounds;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_NOT_ON_THE_SCREEN;
import static io.perfeccionista.framework.exceptions.messages.UtilsMessages.RESPONSE_FORMAT_NOT_VALID;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.measurements.HorizontalDirection.LEFT;
import static io.perfeccionista.framework.measurements.HorizontalDirection.RIGHT;
import static io.perfeccionista.framework.measurements.VerticalDirection.DOWN;
import static io.perfeccionista.framework.measurements.VerticalDirection.UP;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_DOWN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_LEFT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_RIGHT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SCROLL_UP_METHOD;

public class AppiumScrollToHandler implements EndpointHandler<Void> {
    // FIXME getRect на стороне espresso-driver работает аналогично
    private static final Pattern BOUNDS_PATTERN = Pattern.compile("^\\[(?<X1>-?\\d+),(?<Y1>-?\\d+)\\]\\[(?<X2>-?\\d+),(?<Y2>-?\\d+)\\]$");

    // TODO calculate 22px threshold based on device, os or other thinks
    private static final int THRESHOLD = 22;

    private static final String[] VIEW_PORT_ELEMENT_CLASSES = new String[]{
            "android.widget.ScrollView",
            "android.widget.HorizontalScrollView",
            "android.widget.ListView",
            "androidx.core.widget.NestedScrollView"
    };

    private final MobileChildElement element;
    private final AndroidEspressoDriver instance;

    public AppiumScrollToHandler(@NotNull MobileChildElement element) {
        this.element = element;
        this.instance = element.getMobileDeviceDispatcher().getInstance(AndroidEspressoDriver.class);
    }

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    protected Point2D getStartScrollPoint(Direction direction, AndroidElement androidElement, MobileScreenBounds screenBounds) {
        Rectangle viewPortRect = getViewPort(androidElement, screenBounds);

        Point2D elementCenter = getElementCenterPosition(androidElement);

        // @formatter:off
        double horizontalScrollValue =
                elementCenter.getX() <= viewPortRect.getX()
                ? viewPortRect.getX() + viewPortRect.getWidth() / 2.0
                : elementCenter.getX() >= viewPortRect.getX() + viewPortRect.getWidth()
                            ? viewPortRect.getX() + viewPortRect.getWidth() / 2.0
                            : elementCenter.getX();

        double verticalScrollValue =
                elementCenter.getY() <= viewPortRect.getY()
                ? viewPortRect.getY() + viewPortRect.getHeight() / 2.0
                : elementCenter.getY() >= viewPortRect.getY() + viewPortRect.getHeight()
                            ? viewPortRect.getY() + viewPortRect.getHeight() / 2.0
                            : elementCenter.getY();
        // @formatter:on

        return Point2D.of(horizontalScrollValue, verticalScrollValue);
    }

    protected int getScrollLength(Direction direction, AndroidElement androidElement, MobileScreenBounds screenBounds) {
        Rectangle viewPortRect = getViewPort(androidElement, screenBounds);
        Rectangle elementRect = androidElement.getRect();

        Point2D elementCenter = getElementCenterPosition(androidElement);
        double length;
        switch (direction) {
            case UP:
                if (viewPortRect.getHeight() > elementRect.getHeight()) {
                    length = viewPortRect.getY() - elementRect.getY();
                } else {
                    length = screenBounds.getTopBound() - elementCenter.getY();
                }
                break;
            case DOWN:
                if (viewPortRect.getHeight() > elementRect.getHeight()) {
                    length = (elementRect.getY() + elementRect.getHeight()) - (viewPortRect.getY() + viewPortRect.getHeight());
                } else {
                    length = elementCenter.getY() - (viewPortRect.getY() + viewPortRect.getHeight() / 2.0);
                }
                break;
            case LEFT:
                length = screenBounds.getLeftBound() - elementCenter.getX();
                break;
            case RIGHT:
                length = elementCenter.getX() - screenBounds.getRightBound();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }

        return (int) (length/* * 1.3d*/) + THRESHOLD;
    }


    @NotNull
    protected Rectangle getViewPort(AndroidElement androidElement, MobileScreenBounds screenBounds) {
        // TODO сделать поиск с учетом дочернего элемента. Т.е. ближайшего родителя androidElement like espresso: onChildView
        Rectangle viewPortRect = null;
        for (String clazz : VIEW_PORT_ELEMENT_CLASSES) {
            try {
                List<AndroidElement> vpElements = instance.findElementsByClassName(clazz);
                for (AndroidElement vpElement : vpElements) {
                    // TODO прокинуть через MobileDeviceOperationExecutor
                    // TODO другие стратегии
                    if (vpElement.findElementsById(element.getLocatorChain().getLastLocator().getLocatorValue()).size() > 0) {
                        viewPortRect = vpElement.getRect();
                        break;
                    }
                }
            } catch (NoSuchElementException | StaleElementReferenceException ignore) {
            }
            if (viewPortRect != null) {
                break;
            }
        }

        if (viewPortRect != null) {
            // Проверяем что области пересекаются
            if (Math.abs(viewPortRect.x - screenBounds.getLeftBound()) < (Math.abs(viewPortRect.width + screenBounds.getRightBound() - screenBounds.getLeftBound()) / 2)
                    && (Math.abs(viewPortRect.y - screenBounds.getTopBound()) < (Math.abs(viewPortRect.height + screenBounds.getBottomBound() - screenBounds.getTopBound()) / 2))) {
                // Вычисляем пересечение двух областей
                int x = Math.max((int) screenBounds.getLeftBound(), viewPortRect.getX());
                int y = Math.max((int) screenBounds.getTopBound(), viewPortRect.getY());
                return new Rectangle(
                        x,
                        y,
                        Math.min((int) screenBounds.getBottomBound(), viewPortRect.getY() + viewPortRect.getHeight()) - y,
                        Math.min((int) screenBounds.getRightBound(), viewPortRect.getX() + viewPortRect.getWidth()) - x);
            } else {
                // TODO Вьюпорт не видим. Невозможная ситуация
            }
        }

        return new Rectangle(
                (int) screenBounds.getLeftBound(),
                (int) screenBounds.getTopBound(),
                (int) (screenBounds.getBottomBound() - screenBounds.getTopBound()),
                (int) (screenBounds.getRightBound() - screenBounds.getLeftBound()));
    }

    protected boolean isScrollNeeded(Direction direction, AndroidElement androidElement, MobileScreenBounds screenBounds) {
        // FIXME androidElement.getCoordinates().inViewPort() -> UnsupportedCommandException
        return getScrollLength(direction, androidElement, screenBounds) - THRESHOLD > 0;
    }

    @Override
    public Void handle(Object endpoint) {
        AndroidElement androidElement = (AndroidElement) endpoint;

        element.getMobileDeviceDispatcher()
                .keyboard()
                .hideKeyboard();

        MobileScreenBounds screenBounds = element.getMobileDeviceDispatcher()
                .screen()
                .getScreenBounds();

        Point2D elementCenter = getElementCenterPosition(androidElement);

        // Нужно ли скроллить вверх
        if (isScrollNeeded(Direction.UP, androidElement, screenBounds)) {
            runCheck(InvocationName.actionInvocation(SCROLL_UP_METHOD, element, elementCenter), () -> {
                if (isScrollNeeded(Direction.UP, androidElement, screenBounds)) {
                    Point2D startPoint = getStartScrollPoint(Direction.UP, androidElement, screenBounds);
                    int length = getScrollLength(Direction.UP, androidElement, screenBounds);
                    scrollVertically(UP, startPoint, length, screenBounds);
                    throw ElementNotOnTheScreen.exception(ELEMENT_NOT_ON_THE_SCREEN.getMessage(element.getElementIdentifier().getLastUsedName()))
                            .setProcessed(true)
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Screen Bounds", screenBounds.toString()))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Start Position", startPoint.toString()))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Scroll Length", Integer.toString(length)));
                }
            });
        }

        // Нужно ли скроллить вниз
        if (isScrollNeeded(Direction.DOWN, androidElement, screenBounds)) {
            runCheck(InvocationName.actionInvocation(SCROLL_DOWN_METHOD, element, elementCenter), () -> {
                if (isScrollNeeded(Direction.DOWN, androidElement, screenBounds)) {
                    Point2D startPoint = getStartScrollPoint(Direction.DOWN, androidElement, screenBounds);
                    int length = getScrollLength(Direction.DOWN, androidElement, screenBounds);
                    scrollVertically(DOWN, startPoint, length, screenBounds);
                    throw ElementNotOnTheScreen.exception(ELEMENT_NOT_ON_THE_SCREEN.getMessage(element.getElementIdentifier().getLastUsedName()))
                            .setProcessed(true)
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Screen Bounds", screenBounds.toString()))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Start Position", startPoint.toString()))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Scroll Length", Integer.toString(length)));
                }
            });
        }

        // Нужно ли скроллить влево
        if (isScrollNeeded(Direction.LEFT, androidElement, screenBounds)) {
            runCheck(InvocationName.actionInvocation(SCROLL_LEFT_METHOD, element, elementCenter), () -> {
                if (isScrollNeeded(Direction.LEFT, androidElement, screenBounds)) {
                    Point2D startPoint = getStartScrollPoint(Direction.LEFT, androidElement, screenBounds);
                    int length = getScrollLength(Direction.LEFT, androidElement, screenBounds);
                    scrollHorizontally(LEFT, startPoint, length, screenBounds, instance);
                    throw ElementNotOnTheScreen.exception(ELEMENT_NOT_ON_THE_SCREEN.getMessage(element.getElementIdentifier().getLastUsedName()))
                            .setProcessed(true)
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Screen Bounds", screenBounds.toString()))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Start Position", startPoint.toString()))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Scroll Length", Integer.toString(length)));
                }
            });
        }

        // Нужно ли скроллить вправо
        if (isScrollNeeded(Direction.RIGHT, androidElement, screenBounds)) {
            runCheck(InvocationName.actionInvocation(SCROLL_RIGHT_METHOD, element, elementCenter), () -> {
                if (isScrollNeeded(Direction.RIGHT, androidElement, screenBounds)) {
                    Point2D startPoint = getStartScrollPoint(Direction.RIGHT, androidElement, screenBounds);
                    int length = getScrollLength(Direction.RIGHT, androidElement, screenBounds);
                    scrollHorizontally(RIGHT, startPoint, length, screenBounds, instance);
                    throw ElementNotOnTheScreen.exception(ELEMENT_NOT_ON_THE_SCREEN.getMessage(element.getElementIdentifier().getLastUsedName()))
                            .setProcessed(true)
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Screen Bounds", screenBounds.toString()))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Start Position", startPoint.toString()))
                            .addLastAttachmentEntry(TextAttachmentEntry.of("Scroll Length", Integer.toString(length)));
                }
            });
        }

        return null;
    }

    protected Point2D getElementCenterPosition(AndroidElement androidElement) {
        String bounds = androidElement.getAttribute("bounds");
        Matcher boundsMatcher = BOUNDS_PATTERN.matcher(bounds);
        if (boundsMatcher.find()) {
            Double x1 = Double.parseDouble(boundsMatcher.group("X1"));
            Double y1 = Double.parseDouble(boundsMatcher.group("Y1"));
            Double x2 = Double.parseDouble(boundsMatcher.group("X2"));
            Double y2 = Double.parseDouble(boundsMatcher.group("Y2"));

            double elementCenterX = x1 + (x2 - x1) / 2;
            double elementCenterY = y1 + (y2 - y1) / 2;

            return Point2D.of(elementCenterX, elementCenterY);
        }
        throw ResponseFormatIsNotValid.exception(RESPONSE_FORMAT_NOT_VALID.getMessage())
                .addLastAttachmentEntry(TextAttachmentEntry.of("Response", bounds))
                .addLastAttachmentEntry(TextAttachmentEntry.of("RegExp", BOUNDS_PATTERN.pattern()));
    }

    protected void scrollVertically(VerticalDirection scrollDirection, Point2D startPoint, int length, MobileScreenBounds screenBounds) {
        TouchAction touchAction = new TouchAction(this.instance);
        if (DOWN == scrollDirection) {
            touchAction.press(cutBounds((int) startPoint.getX(), (int) startPoint.getY(), screenBounds))
                    .waitAction(WaitOptions.waitOptions(Duration.of(100, ChronoUnit.MILLIS)))
                    .moveTo(cutBounds((int) startPoint.getX(), (int) startPoint.getY() - length, screenBounds))
                    .waitAction(WaitOptions.waitOptions(Duration.of(100, ChronoUnit.MILLIS)))
                    .release()
                    .perform();
        } else {
            touchAction.press(cutBounds((int) startPoint.getX(), (int) startPoint.getY(), screenBounds))
                    .waitAction(WaitOptions.waitOptions(Duration.of(100, ChronoUnit.MILLIS)))
                    .moveTo(cutBounds((int) startPoint.getX(), (int) startPoint.getY() + length, screenBounds))
                    .waitAction(WaitOptions.waitOptions(Duration.of(100, ChronoUnit.MILLIS)))
                    .release()
                    .perform();
        }
    }

    protected void scrollHorizontally(HorizontalDirection scrollDirection, Point2D startPoint, int length, MobileScreenBounds screenBounds, AndroidEspressoDriver instance) {
        TouchAction touchAction = new TouchAction(this.instance);
        if (RIGHT == scrollDirection) {
            touchAction.press(cutBounds((int) startPoint.getX(), (int) startPoint.getY(), screenBounds))
                    .moveTo(cutBounds((int) startPoint.getX() - length, (int) startPoint.getY(), screenBounds))
                    .release()
                    .perform();
        } else {
            touchAction.press(cutBounds((int) startPoint.getX(), (int) startPoint.getY(), screenBounds))
                    .moveTo(cutBounds((int) startPoint.getX() + length, (int) startPoint.getY(), screenBounds))
                    .release()
                    .perform();
        }
    }

    protected PointOption cutBounds(int xPoint, int yPoint, MobileScreenBounds screenBounds) {
        if (xPoint <= screenBounds.getLeftBound()) {
            xPoint = (int) screenBounds.getLeftBound() + 1;
        } else if (xPoint >= screenBounds.getRightBound()) {
            xPoint = (int) screenBounds.getRightBound() - 1;
        }
        if (yPoint <= screenBounds.getTopBound()) {
            yPoint = (int) screenBounds.getTopBound() + 1;
        } else if (yPoint >= screenBounds.getBottomBound()) {
            yPoint = (int) screenBounds.getBottomBound() - 1;
        }
        return PointOption.point(xPoint, yPoint);
    }

}
