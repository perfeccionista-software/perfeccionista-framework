package io.perfeccionista.framework.pagefactory.browser;

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

    default boolean isCloseWebBrowsersAfterTest() {
        return true;
    }

}
