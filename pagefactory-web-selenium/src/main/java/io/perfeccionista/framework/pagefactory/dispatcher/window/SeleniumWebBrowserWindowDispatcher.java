package io.perfeccionista.framework.pagefactory.dispatcher.window;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.pagefactory.dispatcher.executor.WebBrowserOperationExecutor;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.screenshots.PngScreenshot;
import io.perfeccionista.framework.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.actionInvocation;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_ABSOLUTE_WINDOW_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_INNER_WINDOW_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_OUTER_WINDOW_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_GET_PAGE_SCREENSHOT;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SET_WINDOW_FULLSCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SET_ABSOLUTE_WINDOW_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SET_OUTER_WINDOW_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserActionNames.BROWSER_SET_WINDOW_MAXIMIZED_METHOD;
import static io.perfeccionista.framework.pagefactory.dispatcher.window.SeleniumDimensionsConverter.createPerfeccionistaDimensions;
import static io.perfeccionista.framework.pagefactory.dispatcher.window.SeleniumDimensionsConverter.createPerfeccionistaLocation;

public class SeleniumWebBrowserWindowDispatcher implements WebBrowserWindowDispatcher {

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final WebBrowserOperationExecutor operationExecutor;
    protected final WebExceptionMapper exceptionMapper;

    public SeleniumWebBrowserWindowDispatcher(Environment environment, RemoteWebDriver instance, WebExceptionMapper exceptionMapper, WebBrowserOperationExecutor operationExecutor) {
        this.environment = environment;
        this.instance = instance;
        this.operationExecutor = operationExecutor;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public @NotNull Screenshot getPageScreenshot() {
        return MultipleAttemptInvocationWrapper.repeatInvocation(getterInvocation(BROWSER_GET_PAGE_SCREENSHOT), () -> exceptionMapper
                .map(() -> PngScreenshot.from(instance.getScreenshotAs(OutputType.BYTES)))
                .ifException(exception -> {
                    throw exception;
                })
                .getResult());
    }

    @Override
    public @NotNull Dimensions2D getInnerWindowSize() {
        return MultipleAttemptInvocationWrapper.repeatInvocation(getterInvocation(BROWSER_GET_INNER_WINDOW_SIZE_METHOD), () -> exceptionMapper
                .map(() -> {
                    String script = "return window.innerWidth + 'x' + window.innerHeight;";
                    return createPerfeccionistaDimensions(operationExecutor.executeScript(String.class, script));
                })
                .ifException(exception -> {
                    throw exception;
                })
                .getResult());
    }

    @Override
    public @NotNull Dimensions2D getOuterWindowSize() {
        return MultipleAttemptInvocationWrapper.repeatInvocation(getterInvocation(BROWSER_GET_OUTER_WINDOW_SIZE_METHOD), () -> exceptionMapper
                .map(() -> {
                    String script = "return window.outerWidth + 'x' + window.outerHeight;";
                    return createPerfeccionistaDimensions(operationExecutor.executeScript(String.class, script));
                })
                .ifException(exception -> {
                    throw exception;
                })
                .getResult());
    }

    @Override
    public SeleniumWebBrowserWindowDispatcher setOuterWindowSize(@NotNull Dimensions2D windowSize) {
        MultipleAttemptInvocationWrapper.repeatInvocation(actionInvocation(BROWSER_SET_OUTER_WINDOW_SIZE_METHOD, windowSize.toString()), () ->
                exceptionMapper.map(() -> instance.manage().window().setSize(new Dimension(windowSize.getWidthAsInt(), windowSize.getHeightAsInt())))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public @NotNull Point2D getAbsoluteLocation() {
        return MultipleAttemptInvocationWrapper.repeatInvocation(getterInvocation(BROWSER_GET_ABSOLUTE_WINDOW_LOCATION_METHOD), () ->
                exceptionMapper.map(() -> createPerfeccionistaLocation(instance.manage().window().getPosition()))
                        .ifException(exception -> {
                            throw exception;
                        })
                        .getResult());
    }

    @Override
    public SeleniumWebBrowserWindowDispatcher setAbsoluteLocation(@NotNull Point2D location) {
        MultipleAttemptInvocationWrapper.repeatInvocation(actionInvocation(BROWSER_SET_ABSOLUTE_WINDOW_LOCATION_METHOD, location.toString()), () ->
                exceptionMapper.map(() -> instance.manage().window().setPosition(new Point(location.getXAsInt(), location.getYAsInt())))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public SeleniumWebBrowserWindowDispatcher fullscreen() {
        MultipleAttemptInvocationWrapper.repeatInvocation(actionInvocation(BROWSER_SET_WINDOW_FULLSCREEN_METHOD), () ->
                exceptionMapper.map(() -> instance.manage().window().fullscreen())
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public SeleniumWebBrowserWindowDispatcher maximize() {
        MultipleAttemptInvocationWrapper.repeatInvocation(actionInvocation(BROWSER_SET_WINDOW_MAXIMIZED_METHOD), () ->
                exceptionMapper.map(() -> instance.manage().window().maximize())
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

}
