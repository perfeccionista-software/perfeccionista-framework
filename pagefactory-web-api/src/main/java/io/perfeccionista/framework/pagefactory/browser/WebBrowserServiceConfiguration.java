package io.perfeccionista.framework.pagefactory.browser;

import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
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
    Map<Class<? extends ExceptionMapper>, ExceptionMapper> getExceptionMappers();

    /**
     *
     * @return
     */
    default boolean isCloseWebBrowsersAfterTest() {
        return true;
    }

}
