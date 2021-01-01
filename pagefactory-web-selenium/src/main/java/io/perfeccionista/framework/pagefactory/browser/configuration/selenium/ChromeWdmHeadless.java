package io.perfeccionista.framework.pagefactory.browser.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.WdmWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.ChromeType;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWdmHeadless implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        return new WdmWebBrowserSeleniumDispatcher<>(Environment.getCurrent(), new ChromeType())
                .withOptions(options);
    }



}
