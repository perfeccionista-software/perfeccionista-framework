package io.perfeccionista.framework.pagefactory.dispatcher.window;

import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

public interface WebBrowserWindowDispatcher {

    @NotNull Screenshot getPageScreenshot();

    @NotNull Dimensions2D getInnerWindowSize();

    @NotNull Dimensions2D getOuterWindowSize();

    WebBrowserWindowDispatcher setOuterWindowSize(@NotNull Dimensions2D windowSize);

    @NotNull Point2D getAbsoluteLocation();

    WebBrowserWindowDispatcher setAbsoluteLocation(@NotNull Point2D location);

    WebBrowserWindowDispatcher fullscreen();

    WebBrowserWindowDispatcher maximize();

//    @Override
//    public @NotNull Screenshot getPageScreenshot() {
//        return runCheck(environment, getterInvocation(BROWSER_GET_ACTIVE_TAB_SCREENSHOT_METHOD), () ->
//                exceptionMapper.map(() -> {
//                    RemoteWebDriver instance = dispatcher.getInstance(RemoteWebDriver.class);
//                    return PngScreenshot.from(instance.getScreenshotAs(OutputType.BYTES));
//                }))
//                .ifException(exception -> {
//                    throw exception;
//                })
//                .getResult();
//    }
}
