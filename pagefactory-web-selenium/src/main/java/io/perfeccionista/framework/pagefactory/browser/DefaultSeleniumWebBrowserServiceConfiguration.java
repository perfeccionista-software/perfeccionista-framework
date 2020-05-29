package io.perfeccionista.framework.pagefactory.browser;

import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.browser.configuration.DefaultWdmChromeWebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;

import java.util.HashMap;
import java.util.Map;

public class DefaultSeleniumWebBrowserServiceConfiguration implements WebBrowserServiceConfiguration {

    @Override
    public Map<String, WebBrowserConfiguration> getWebBrowserConfigurations() {
        Map<String, WebBrowserConfiguration> browsers = new HashMap<>();
        browsers.put("DefaultWdmChrome", new DefaultWdmChromeWebBrowserConfiguration());
        return browsers;
    }

    @Override
    public Map<Class<? extends ExceptionMapper>, ExceptionMapper> getExceptionMappers() {
        Map<Class<? extends ExceptionMapper>, ExceptionMapper> mappers = new HashMap<>();
        mappers.put(SeleniumExceptionMapper.class, new SeleniumExceptionMapper());
        return mappers;
    }

}
