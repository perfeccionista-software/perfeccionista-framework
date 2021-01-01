package io.perfeccionista.framework.pagefactory.browser;

import io.perfeccionista.framework.exceptions.mapper.ExceptionMapper;
import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.ChromeLocal;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.ChromeRemote;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.ChromeWdm;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.ChromeWdmHeadless;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.EdgeLocal;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.EdgeWdm;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.FirefoxLocal;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.FirefoxRemote;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.FirefoxWdm;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.OperaLocal;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.OperaRemote;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.OperaWdm;
import io.perfeccionista.framework.pagefactory.browser.configuration.selenium.SafariLocal;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultSeleniumWebBrowserServiceConfiguration implements WebBrowserServiceConfiguration {

    @Override
    public Map<String, WebBrowserConfiguration> getWebBrowserConfigurations() {
        return Stream.of(
                Map.entry("Chrome Local", new ChromeLocal()),
                Map.entry("Chrome Remote", new ChromeRemote()),
                Map.entry("Chrome Wdm", new ChromeWdm()),
                Map.entry("Chrome Wdm Headless", new ChromeWdmHeadless()),
                Map.entry("Edge Local", new EdgeLocal()),
                Map.entry("Edge Wdm", new EdgeWdm()),
                Map.entry("Firefox Local", new FirefoxLocal()),
                Map.entry("Firefox Remote", new FirefoxRemote()),
                Map.entry("Firefox Wdm", new FirefoxWdm()),
                Map.entry("Opera Local", new OperaLocal()),
                Map.entry("Opera Remote", new OperaRemote()),
                Map.entry("Opera Wdm", new OperaWdm()),
                Map.entry("Safari Local", new SafariLocal())
        ).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    @Override
    public Map<Class<? extends ExceptionMapper>, ExceptionMapper> getExceptionMappers() {
        return Stream.of(
                Map.entry(SeleniumExceptionMapper.class, new SeleniumExceptionMapper())
        ).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

}
