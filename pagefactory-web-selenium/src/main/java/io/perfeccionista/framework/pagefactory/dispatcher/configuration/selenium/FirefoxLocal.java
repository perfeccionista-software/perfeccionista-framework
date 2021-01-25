package io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserBinaryResolver;
import io.perfeccionista.framework.pagefactory.dispatcher.LocalWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.type.FirefoxType;
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

    protected WebBrowserBinaryResolver getWebDriverBinaryResolver() {
        return WebBrowserBinaryResolver.of(Path.of(stringProcess("${[config] perfeccionista.browser.firefox.webdriver}")));
    }

}

