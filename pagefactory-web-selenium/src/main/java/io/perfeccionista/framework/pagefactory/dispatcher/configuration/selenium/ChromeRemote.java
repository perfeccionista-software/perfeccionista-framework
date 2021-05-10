package io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.RemoteWebBrowserSeleniumDispatcher;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.perfeccionista.framework.value.Values.stringProcess;

public class ChromeRemote implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setAcceptInsecureCerts(true);
        return new RemoteWebBrowserSeleniumDispatcher(Environment.getCurrent(), stringProcess("${[config] perfeccionista.browser.chrome.remote}"))
                .withOptions(capabilities);
    }

}
