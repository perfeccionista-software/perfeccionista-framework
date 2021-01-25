package io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserBinaryResolver;
import io.perfeccionista.framework.pagefactory.dispatcher.LocalWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.type.ChromeType;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Path;

import static io.perfeccionista.framework.utils.StringUtils.isNotBlank;
import static io.perfeccionista.framework.value.Values.stringProcess;

public class ChromeLocal implements WebBrowserConfiguration {

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
        String chromeBinaryPath = stringProcess("${[config] perfeccionista.browser.chrome.binary}");
        if (isNotBlank(chromeBinaryPath)) {
            options.setBinary(chromeBinaryPath);
        }
        return new LocalWebBrowserSeleniumDispatcher<>(Environment.getCurrent(), new ChromeType(), getWebDriverBinaryResolver())
                .withOptions(options);
    }

    protected WebBrowserBinaryResolver getWebDriverBinaryResolver() {
        return WebBrowserBinaryResolver.of(Path.of(stringProcess("${[config] perfeccionista.browser.chrome.webdriver}")));
    }

}
