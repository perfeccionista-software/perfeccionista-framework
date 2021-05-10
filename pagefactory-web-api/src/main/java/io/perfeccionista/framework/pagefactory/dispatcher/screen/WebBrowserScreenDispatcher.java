package io.perfeccionista.framework.pagefactory.dispatcher.screen;

import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserScreenDispatcher {

    @NotNull Screenshot getPageScreenshot();

}
