package io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserBinaryResolver;
import io.perfeccionista.framework.pagefactory.dispatcher.LocalWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.type.SafariType;
import org.openqa.selenium.safari.SafariOptions;

import java.nio.file.Paths;

import static io.perfeccionista.framework.value.Values.stringProcess;

public class SafariLocal implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        SafariOptions options = new SafariOptions();
        return new LocalWebBrowserSeleniumDispatcher<>(Environment.getCurrent(), new SafariType(), getWebDriverBinaryResolver())
                .withOptions(options);
    }

    protected WebBrowserBinaryResolver getWebDriverBinaryResolver() {
        return WebBrowserBinaryResolver.of(Paths.get(stringProcess("${[config] perfeccionista.browser.safari.webdriver}")));
    }

}

