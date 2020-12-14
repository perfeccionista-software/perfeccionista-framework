package io.perfeccionista.framework.pagefactory.configurations.browser;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebDriverBinaryResolver;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.LocalWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.ChromeType;

import java.nio.file.Path;

public class ChromeDefaultWebBrowserConfiguration implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get(Environment environment) {
        return new LocalWebBrowserSeleniumDispatcher<>(environment, new ChromeType(), getWebDriverBinaryResolver())
                .withTraceSearch(false);
    }

    protected WebDriverBinaryResolver getWebDriverBinaryResolver() {
        return WebDriverBinaryResolver.of(Path.of("/Users/irsv/Downloads/chromedriver"));
    }

}
