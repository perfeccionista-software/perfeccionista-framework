package io.perfeccionista.framework.pagefactory.browser.window;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.browser.executor.OperationExecutor;
import io.perfeccionista.framework.pagefactory.elements.methods.Dimensions;
import io.perfeccionista.framework.pagefactory.elements.methods.Location;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumWindowDispatcher implements WindowDispatcher {

    protected final Environment environment;
    protected final RemoteWebDriver webDriver;
    protected final OperationExecutor operationExecutor;
    protected final ExceptionMapper exceptionMapper;

    public SeleniumWindowDispatcher(Environment environment, RemoteWebDriver webDriver, ExceptionMapper exceptionMapper, OperationExecutor operationExecutor) {
        this.environment = environment;
        this.webDriver = webDriver;
        this.operationExecutor = operationExecutor;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public Dimensions getWindowSize() {
        return null;
    }

    @Override
    public WindowDispatcher setWindowSize(int width, int height) {
        this.exceptionMapper.map(() -> this.webDriver.manage().window().setSize(new Dimension(width, height)));
        return this;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public WindowDispatcher setLocation(Location location) {
        // TODO: window.moveTo() in JS
//        this.exceptionMapper.map(() -> this.webDriver.manage().window().setPosition());
        return this;
    }

    @Override
    public WindowDispatcher fullscreen(boolean fullscreen) {
        // TODO: Проверить что сейчас браузер находится не в фулскрине
        if (fullscreen) {
            this.exceptionMapper.map(() -> this.webDriver.manage().window().fullscreen());
        }
        // TODO: Вывести из фулскрина
        return this;
    }

    @Override
    public WindowDispatcher maximize() {
        this.exceptionMapper.map(() -> this.webDriver.manage().window().maximize());
        return this;
    }

}
