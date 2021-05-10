package io.perfeccionista.framework.pagefactory.dispatcher.logs;

import io.appium.java_client.AppiumDriver;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;

import java.util.List;

public class DefaultAppiumIosLogsDispatcher implements MobileDeviceLogsDispatcher {

    protected final Environment environment;
    protected final AppiumDriver<?> instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumIosLogsDispatcher(Environment environment, AppiumDriver<?> instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public List<String> getLogs() {
        return null;
    }

}
