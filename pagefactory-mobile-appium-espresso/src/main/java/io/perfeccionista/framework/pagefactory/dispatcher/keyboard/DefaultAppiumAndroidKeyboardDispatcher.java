package io.perfeccionista.framework.pagefactory.dispatcher.keyboard;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.pagefactory.dispatcher.driver.AndroidEspressoDriver;

public class DefaultAppiumAndroidKeyboardDispatcher implements MobileDeviceKeyboardDispatcher {

    protected final Environment environment;
    protected final AndroidEspressoDriver instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumAndroidKeyboardDispatcher(Environment environment, AndroidEspressoDriver instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public boolean isKeyboardShown() {
        return instance.isKeyboardShown();
    }

    @Override
    public DefaultAppiumAndroidKeyboardDispatcher hideKeyboard() {
        instance.hideKeyboard();
        return this;
    }

}
