package io.perfeccionista.framework.pagefactory.browser.registry;

import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserConfiguration;

import java.util.Map;

public class WebBrowserDispatcherRegistry {

    private Map<String, WebBrowserConfiguration> webDriverInstanceConfigurations;

    public WebBrowserDispatcherRegistry(Map<String, WebBrowserConfiguration> webDriverInstanceConfigurations) {
        this.webDriverInstanceConfigurations = webDriverInstanceConfigurations;
    }

    public void init() {

    }

    public WebBrowserDispatcher getDefault() {

        return null;
    }

    public WebBrowserDispatcher getByName(String browserDispatcherName) {

        return null;
    }

}
