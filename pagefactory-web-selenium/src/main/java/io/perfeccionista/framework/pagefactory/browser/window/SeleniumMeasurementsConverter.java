package io.perfeccionista.framework.pagefactory.browser.window;

import io.perfeccionista.framework.exceptions.WebBrowserDimensions;
import io.perfeccionista.framework.measurements.Dimensions;
import io.perfeccionista.framework.measurements.Location;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Point;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.WEB_BROWSER_DIMENSIONS_FORMAT_UNSUPPORTED;

public class SeleniumMeasurementsConverter {
    private static final Pattern DIMENSIONS_PATTERN = Pattern.compile("^(?<width>\\d+)x(?<height>\\d+)$");

    private SeleniumMeasurementsConverter() {
    }

    public static @NotNull Dimensions createPerfeccionistaDimensions(@NotNull String dimensions) {
        Matcher matcher = DIMENSIONS_PATTERN.matcher(dimensions);
        if (matcher.find()) {
            int width = Integer.parseInt(matcher.group("width"));
            int height = Integer.parseInt(matcher.group("height"));
            return Dimensions.of(width, height);
        }
        throw WebBrowserDimensions.exception(WEB_BROWSER_DIMENSIONS_FORMAT_UNSUPPORTED.getMessage(dimensions));
    }

    public static @NotNull Location createPerfeccionistaLocation(@NotNull Point point) {
        return Location.absolute(point.getX(), point.getY());
    }

}
