package io.perfeccionista.framework.pagefactory.browser.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.RemoteWebBrowserSeleniumDispatcher;
import org.openqa.selenium.opera.OperaOptions;

import static io.perfeccionista.framework.value.Values.stringProcess;

public class OperaRemote implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        return new RemoteWebBrowserSeleniumDispatcher(Environment.getCurrent(), stringProcess("${[config] perfeccionista.browser.opera.remote}"))
                .withOptions(new OperaOptions());
    }

}
