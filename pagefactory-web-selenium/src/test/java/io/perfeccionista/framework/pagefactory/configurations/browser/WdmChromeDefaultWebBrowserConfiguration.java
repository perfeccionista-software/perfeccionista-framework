package io.perfeccionista.framework.pagefactory.configurations.browser;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.WdmWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.ChromeType;
import org.openqa.selenium.chrome.ChromeOptions;

public class WdmChromeDefaultWebBrowserConfiguration implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get(Environment environment) {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        return new WdmWebBrowserSeleniumDispatcher<>(environment, new ChromeType())
                .withOptions(options);
    }

}
