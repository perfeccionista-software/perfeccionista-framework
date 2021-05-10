package io.perfeccionista.framework.pagefactory.dispatcher.window;

import io.perfeccionista.framework.exceptions.WebBrowserDimensions;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.measurements.Point2D;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Point;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_DIMENSIONS_FORMAT_UNSUPPORTED;

public class SeleniumDimensionsConverter {
    private static final Pattern DIMENSIONS_PATTERN = Pattern.compile("^(?<width>\\d+)x(?<height>\\d+)$");

    private SeleniumDimensionsConverter() {
    }

    public static @NotNull Dimensions2D createPerfeccionistaDimensions(@NotNull String dimensions) {
        Matcher matcher = DIMENSIONS_PATTERN.matcher(dimensions);
        if (matcher.find()) {
            int width = Integer.parseInt(matcher.group("width"));
            int height = Integer.parseInt(matcher.group("height"));
            return Dimensions2D.of(width, height);
        }
        throw WebBrowserDimensions.exception(WEB_BROWSER_DIMENSIONS_FORMAT_UNSUPPORTED.getMessage(dimensions));
    }

    public static @NotNull Point2D createPerfeccionistaLocation(@NotNull Point point) {
        return Point2D.of(point.getX(), point.getY());
    }

}
