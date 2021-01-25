package io.perfeccionista.framework.pagefactory.dispatcher.logs;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.pagefactory.dispatcher.driver.AndroidEspressoDriver;

import java.util.List;

public class DefaultAppiumAndroidLogsDispatcher implements MobileDeviceLogsDispatcher {

    protected final Environment environment;
    protected final AndroidEspressoDriver instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumAndroidLogsDispatcher(Environment environment, AndroidEspressoDriver instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public List<String> getLogs() {
        return null;
    }

}
