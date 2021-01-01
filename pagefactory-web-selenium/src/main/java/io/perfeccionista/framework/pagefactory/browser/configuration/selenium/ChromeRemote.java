package io.perfeccionista.framework.pagefactory.browser.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.RemoteWebBrowserSeleniumDispatcher;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.perfeccionista.framework.value.Values.stringProcess;

public class ChromeRemote implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-software-rasterizer");
        return new RemoteWebBrowserSeleniumDispatcher(Environment.getCurrent(), stringProcess("${[config] perfeccionista.browser.chrome.remote}"))
                .withOptions(options);
    }

}
