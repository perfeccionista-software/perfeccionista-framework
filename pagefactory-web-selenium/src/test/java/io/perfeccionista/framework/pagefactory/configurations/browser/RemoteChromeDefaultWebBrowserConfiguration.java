package io.perfeccionista.framework.pagefactory.configurations.browser;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.RemoteWebBrowserSeleniumDispatcher;
import org.openqa.selenium.chrome.ChromeOptions;

public class RemoteChromeDefaultWebBrowserConfiguration implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get(Environment environment) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-software-rasterizer");
        return new RemoteWebBrowserSeleniumDispatcher(environment, "http://localhost:4444/wd/hub")
                .withOptions(options);
    }

}
