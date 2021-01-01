package io.perfeccionista.framework.pagefactory.browser.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebDriverBinaryResolver;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.LocalWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.OperaType;
import org.openqa.selenium.opera.OperaOptions;

import java.nio.file.Path;

import static io.perfeccionista.framework.utils.StringUtils.isNotBlank;
import static io.perfeccionista.framework.value.Values.stringProcess;

public class OperaLocal implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        OperaOptions options = new OperaOptions();
        String operaBinaryPath = stringProcess("${[config] perfeccionista.browser.opera.binary}");
        if (isNotBlank(operaBinaryPath)) {
            options.setBinary(operaBinaryPath);
        }
        return new LocalWebBrowserSeleniumDispatcher<>(Environment.getCurrent(), new OperaType(), getWebDriverBinaryResolver())
                .withOptions(options);
    }

    protected WebDriverBinaryResolver getWebDriverBinaryResolver() {
        return WebDriverBinaryResolver.of(Path.of(stringProcess("${[config] perfeccionista.browser.opera.webdriver}")));
    }

}
