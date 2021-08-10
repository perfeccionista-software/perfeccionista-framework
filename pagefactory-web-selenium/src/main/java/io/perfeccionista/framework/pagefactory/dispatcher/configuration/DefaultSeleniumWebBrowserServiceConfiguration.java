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

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultSeleniumWebBrowserServiceConfiguration implements WebBrowserServiceConfiguration {

    @Override
    public Map<String, WebBrowserConfiguration> getWebBrowserConfigurations() {
        return Stream.of(
                new SimpleEntry<>("Chrome Local", new ChromeLocal()),
                new SimpleEntry<>("Chrome Remote", new ChromeRemote()),
                new SimpleEntry<>("Chrome Wdm", new ChromeWdm()),
                new SimpleEntry<>("Chrome Wdm Headless", new ChromeWdmHeadless()),
                new SimpleEntry<>("Edge Local", new EdgeLocal()),
                new SimpleEntry<>("Edge Wdm", new EdgeWdm()),
                new SimpleEntry<>("Firefox Local", new FirefoxLocal()),
                new SimpleEntry<>("Firefox Remote", new FirefoxRemote()),
                new SimpleEntry<>("Firefox Wdm", new FirefoxWdm()),
                new SimpleEntry<>("Opera Local", new OperaLocal()),
                new SimpleEntry<>("Opera Remote", new OperaRemote()),
                new SimpleEntry<>("Opera Wdm", new OperaWdm()),
                new SimpleEntry<>("Safari Local", new SafariLocal())
        ).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

    @Override
    public Map<Class<? extends WebExceptionMapper>, WebExceptionMapper> getExceptionMappers() {
        return Stream.of(
                new SimpleEntry<>(SeleniumExceptionMapper.class, new SeleniumExceptionMapper())
        ).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }

}
