package io.perfeccionista.framework.pagefactory.dispatcher.keyboard;

import io.appium.java_client.AppiumDriver;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;

public class DefaultAppiumIosKeyboardDispatcher implements MobileDeviceKeyboardDispatcher {

    protected final Environment environment;
    protected final AppiumDriver<?> instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumIosKeyboardDispatcher(Environment environment, AppiumDriver<?> instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public boolean isKeyboardShown() {
        return false;
    }

    @Override
    public MobileDeviceKeyboardDispatcher hideKeyboard() {
        return null;
    }

}
