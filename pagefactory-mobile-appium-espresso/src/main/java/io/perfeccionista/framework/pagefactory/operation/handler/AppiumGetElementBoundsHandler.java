package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.android.AndroidElement;
import io.perfeccionista.framework.exceptions.ElementBoundsParse;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileIsDisplayedAvailable;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_BOUNDS_CANT_BE_PARSED;

public class AppiumGetElementBoundsHandler implements EndpointHandler<ElementBounds> {
    public static final Pattern BOUNDS_REGEXP = Pattern.compile("^\\[(?<X1>\\d+),(?<Y1>\\d+)\\]\\[(?<X2>\\d+),(?<Y2>\\d+)\\]$");

    private final MobileChildElementBase element;
    private final String component;

    public AppiumGetElementBoundsHandler(@NotNull MobileChildElementBase element, String component) {
        this.element = element;
        this.component = component;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return null;
    }

    @Override
    public ElementBounds handle(Object endpoint) {
        String bounds = ((AndroidElement) endpoint).getAttribute("bounds");
        Matcher matcher = BOUNDS_REGEXP.matcher(bounds);
        if (matcher.find()) {
            Double x1 = Double.valueOf(matcher.group("X1"));
            Double y1 = Double.valueOf(matcher.group("Y1"));
            Double x2 = Double.valueOf(matcher.group("X2"));
            Double y2 = Double.valueOf(matcher.group("Y2"));

            double width = x2 - x1;
            double height = y2 - y1;
            double screenLeft = x1;
            double screenTop = y1;
            double centerX = x1 + (width / 2);
            double centerY = y1 + (height / 2);

            return ElementBounds.of(width, height, screenLeft, screenTop, centerX, centerY);
        }
        throw ElementBoundsParse.exception(ELEMENT_BOUNDS_CANT_BE_PARSED.getMessage(bounds));
    }

}
