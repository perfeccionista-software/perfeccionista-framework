package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Map;

public interface WebBrowserServiceConfiguration extends ServiceConfiguration {

    /**
     * name
     * driverInstance
     * timeouts
     * @return
     */
    Map<String, WebBrowserConfiguration> getWebBrowserConfigurations();

    /**
     *
     * @return
     */
    Map<Class<? extends WebExceptionMapper>, WebExceptionMapper> getExceptionMappers();

    /**
     *
     * @return
     */
    default boolean isCloseWebBrowsersAfterTest() {
        return true;
    }

}
