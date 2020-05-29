package io.perfeccionista.framework.pagefactory.browser.window;

import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;

public interface WindowDispatcher {

    Dimensions getWindowSize();

    WindowDispatcher setWindowSize(int width, int height);

    Location getLocation();

    WindowDispatcher setLocation(Location location);

    WindowDispatcher fullscreen(boolean fullscreen);

    WindowDispatcher maximize();

}
