package io.perfeccionista.framework.pagefactory.browser.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebDriverBinaryResolver;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.LocalWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.SafariType;
import org.openqa.selenium.safari.SafariOptions;

import java.nio.file.Path;

import static io.perfeccionista.framework.value.Values.stringProcess;

public class SafariLocal implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        SafariOptions options = new SafariOptions();
        return new LocalWebBrowserSeleniumDispatcher<>(Environment.getCurrent(), new SafariType(), getWebDriverBinaryResolver())
                .withOptions(options);
    }

    protected WebDriverBinaryResolver getWebDriverBinaryResolver() {
        return WebDriverBinaryResolver.of(Path.of(stringProcess("${[config] perfeccionista.browser.safari.webdriver}")));
    }

}

