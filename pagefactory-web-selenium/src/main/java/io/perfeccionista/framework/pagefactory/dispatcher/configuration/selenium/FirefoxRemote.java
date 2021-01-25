package io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.RemoteWebBrowserSeleniumDispatcher;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.perfeccionista.framework.value.Values.stringProcess;

public class FirefoxRemote implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        DesiredCapabilities options = new DesiredCapabilities();
        options.setBrowserName("firefox");
        options.setCapability("enableVNC", true);
        options.setCapability("enableVideo", false);
        return new RemoteWebBrowserSeleniumDispatcher(Environment.getCurrent(), stringProcess("${[config] perfeccionista.browser.firefox.remote}"))
                .withOptions(options);
    }

}

