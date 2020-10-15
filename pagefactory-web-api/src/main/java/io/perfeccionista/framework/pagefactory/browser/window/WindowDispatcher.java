package io.perfeccionista.framework.pagefactory.browser.window;

import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.measurements.Dimensions;

public interface WindowDispatcher {

    Dimensions getWindowSize();

    WindowDispatcher setWindowSize(int width, int height);

    Location getLocation();

    WindowDispatcher setLocation(Location location);

    WindowDispatcher fullscreen(boolean fullscreen);

    WindowDispatcher maximize();

}
