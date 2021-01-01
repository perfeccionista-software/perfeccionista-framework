package io.perfeccionista.framework.pagefactory.browser.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebDriverBinaryResolver;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.LocalWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.FirefoxType;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.nio.file.Path;

import static io.perfeccionista.framework.utils.StringUtils.isNotBlank;
import static io.perfeccionista.framework.value.Values.stringProcess;

public class FirefoxLocal implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        FirefoxOptions options = new FirefoxOptions();
        String firefoxBinaryPath = stringProcess("${[config] perfeccionista.browser.firefox.binary}");
        if (isNotBlank(firefoxBinaryPath)) {
            options.setBinary(firefoxBinaryPath);
        }
        return new LocalWebBrowserSeleniumDispatcher<>(Environment.getCurrent(), new FirefoxType(), getWebDriverBinaryResolver())
                .withOptions(options);
    }

    protected WebDriverBinaryResolver getWebDriverBinaryResolver() {
        return WebDriverBinaryResolver.of(Path.of(stringProcess("${[config] perfeccionista.browser.firefox.webdriver}")));
    }

}

