package io.perfeccionista.framework.pagefactory.browser.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.RemoteWebBrowserSeleniumDispatcher;
import org.openqa.selenium.firefox.FirefoxOptions;

import static io.perfeccionista.framework.value.Values.stringProcess;

public class FirefoxRemote implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        return new RemoteWebBrowserSeleniumDispatcher(Environment.getCurrent(), stringProcess("${[config] perfeccionista.browser.firefox.remote}"))
                .withOptions(new FirefoxOptions());
    }

}

