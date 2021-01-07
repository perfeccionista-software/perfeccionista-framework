package io.perfeccionista.framework.pagefactory.browser.window;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.browser.executor.OperationExecutor;
import io.perfeccionista.framework.measurements.Dimensions;
import io.perfeccionista.framework.measurements.Location;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.RemoteWebDriver;

import static io.perfeccionista.framework.invocation.runner.InvocationName.getterInvocation;
import static io.perfeccionista.framework.invocation.wrapper.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_GET_ABSOLUTE_WINDOW_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_GET_INNER_WINDOW_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_GET_OUTER_WINDOW_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_SET_WINDOW_FULLSCREEN_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_SET_ABSOLUTE_WINDOW_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_SET_OUTER_WINDOW_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.WebBrowserActionNames.BROWSER_SET_WINDOW_MAXIMIZED_METHOD;
import static io.perfeccionista.framework.pagefactory.browser.window.SeleniumMeasurementsConverter.createPerfeccionistaDimensions;
import static io.perfeccionista.framework.pagefactory.browser.window.SeleniumMeasurementsConverter.createPerfeccionistaLocation;

public class SeleniumWindowDispatcher implements WindowDispatcher {

    protected final Environment environment;
    protected final RemoteWebDriver instance;
    protected final OperationExecutor operationExecutor;
    protected final ExceptionMapper exceptionMapper;

    public SeleniumWindowDispatcher(Environment environment, RemoteWebDriver instance, ExceptionMapper exceptionMapper, OperationExecutor operationExecutor) {
        this.environment = environment;
        this.instance = instance;
        this.operationExecutor = operationExecutor;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public Dimensions getInnerWindowSize() {
        return runCheck(environment, getterInvocation(BROWSER_GET_INNER_WINDOW_SIZE_METHOD), () -> exceptionMapper
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
    public Dimensions getOuterWindowSize() {
        return runCheck(environment, getterInvocation(BROWSER_GET_OUTER_WINDOW_SIZE_METHOD), () -> exceptionMapper
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
    public SeleniumWindowDispatcher setOuterWindowSize(int width, int height) {
        runCheck(environment, getterInvocation(BROWSER_SET_OUTER_WINDOW_SIZE_METHOD, width, height), () ->
                exceptionMapper.map(() -> instance.manage().window().setSize(new Dimension(width, height)))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public Location getAbsoluteLocation() {
        return runCheck(environment, getterInvocation(BROWSER_GET_ABSOLUTE_WINDOW_LOCATION_METHOD), () ->
                exceptionMapper.map(() -> createPerfeccionistaLocation(instance.manage().window().getPosition()))
                        .ifException(exception -> {
                            throw exception;
                        })
                        .getResult());
    }

    @Override
    public SeleniumWindowDispatcher setAbsoluteLocation(int absoluteX, int absoluteY) {
        runCheck(environment, getterInvocation(BROWSER_SET_ABSOLUTE_WINDOW_LOCATION_METHOD, absoluteX, absoluteY), () ->
                exceptionMapper.map(() -> instance.manage().window().setPosition(new Point(absoluteX, absoluteY)))
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public SeleniumWindowDispatcher fullscreen() {
        runCheck(environment, getterInvocation(BROWSER_SET_WINDOW_FULLSCREEN_METHOD), () ->
                exceptionMapper.map(() -> instance.manage().window().fullscreen())
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

    @Override
    public SeleniumWindowDispatcher maximize() {
        runCheck(environment, getterInvocation(BROWSER_SET_WINDOW_MAXIMIZED_METHOD), () ->
                exceptionMapper.map(() -> instance.manage().window().maximize())
                        .ifException(exception -> {
                            throw exception;
                        }));
        return this;
    }

}
