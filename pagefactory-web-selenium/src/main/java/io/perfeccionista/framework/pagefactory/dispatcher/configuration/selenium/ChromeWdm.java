package io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.WdmWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.type.ChromeType;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeWdm implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.setAcceptInsecureCerts(true);
        return new WdmWebBrowserSeleniumDispatcher<>(Environment.getCurrent(), new ChromeType())
                .withOptions(options);
    }

}
