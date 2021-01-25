package io.perfeccionista.framework.pagefactory.dispatcher.browser;

import io.appium.java_client.AppiumDriver;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;

@Deprecated
public class DefaultAppiumMobileBrowserDispatcher implements MobileBrowserDispatcher {

    protected final Environment environment;
    protected final AppiumDriver<?> instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumMobileBrowserDispatcher(Environment environment, AppiumDriver<?> instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }


}
