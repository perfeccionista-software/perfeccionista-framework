package io.perfeccionista.framework.pagefactory.configurations;

import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserServiceConfiguration;
import io.perfeccionista.framework.pagefactory.configurations.browser.ChromeDefaultWebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.configurations.browser.RemoteChromeDefaultWebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.configurations.browser.WdmChromeDefaultWebBrowserConfiguration;

import java.util.HashMap;
import java.util.Map;

public class TestWebBrowserServiceConfiguration implements WebBrowserServiceConfiguration {

    @Override
    public Map<String, WebBrowserConfiguration> getWebBrowserConfigurations() {
        HashMap<String, WebBrowserConfiguration> browsers = new HashMap<>();
        browsers.put("Chrome", new ChromeDefaultWebBrowserConfiguration());
        browsers.put("WdmChrome", new WdmChromeDefaultWebBrowserConfiguration());
        browsers.put("RemoteChrome", new RemoteChromeDefaultWebBrowserConfiguration());
        return browsers;
    }

    @Override
    public Map<Class<? extends ExceptionMapper>, ExceptionMapper> getExceptionMappers() {
        return new HashMap<>();
    }

}