package io.perfeccionista.framework.pagefactory.browser.window;

import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.measurements.Dimensions;

public interface WindowDispatcher {

    Dimensions getInnerWindowSize();

    Dimensions getOuterWindowSize();

    WindowDispatcher setOuterWindowSize(int width, int height);

    Location getAbsoluteLocation();

    WindowDispatcher setAbsoluteLocation(int absoluteX, int absoluteY);

    WindowDispatcher fullscreen();

    WindowDispatcher maximize();

}
