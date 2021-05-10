package io.perfeccionista.framework.pagefactory.dispatcher.configuration;

import io.perfeccionista.framework.exceptions.mapper.SeleniumExceptionMapper;
import io.perfeccionista.framework.exceptions.mapper.WebExceptionMapper;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.ChromeLocal;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.ChromeRemote;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.ChromeWdm;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.ChromeWdmHeadless;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.EdgeLocal;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.EdgeWdm;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.FirefoxLocal;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.FirefoxRemote;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.FirefoxWdm;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.OperaLocal;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.OperaRemote;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.OperaWdm;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium.SafariLocal;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserServiceConfiguration;

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
    public Map<Class<? extends WebExceptionMapper>, WebExceptionMapper> getExceptionMappers() {
        return Stream.of(
                Map.entry(SeleniumExceptionMapper.class, new SeleniumExceptionMapper())
        ).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

}
