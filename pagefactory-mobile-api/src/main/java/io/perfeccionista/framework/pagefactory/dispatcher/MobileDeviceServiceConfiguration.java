package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.MobileDeviceConfiguration;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Map;

public interface MobileDeviceServiceConfiguration extends ServiceConfiguration {

    /**
     * name
     * driverInstance
     * timeouts
     * @return
     */
    Map<String, MobileDeviceConfiguration> getMobileDeviceConfigurations();

    /**
     *
     * @return
     */
    Map<Class<? extends MobileExceptionMapper>, MobileExceptionMapper> getExceptionMappers();

    /**
     *
     * @return
     */
    default boolean isCloseMobileDevicesAfterTest() {
        return true;
    }

}
