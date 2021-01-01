package io.perfeccionista.framework.pagefactory.browser.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebDriverBinaryResolver;
import io.perfeccionista.framework.pagefactory.browser.dispatcher.LocalWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.EdgeType;
import org.openqa.selenium.edge.EdgeOptions;

import java.nio.file.Path;

import static io.perfeccionista.framework.value.Values.stringProcess;

public class EdgeLocal implements WebBrowserConfiguration {

    @Override
    public WebBrowserDispatcher get() {
        EdgeOptions options = new EdgeOptions();
        return new LocalWebBrowserSeleniumDispatcher<>(Environment.getCurrent(), new EdgeType(), getWebDriverBinaryResolver())
                .withOptions(options);
    }

    protected WebDriverBinaryResolver getWebDriverBinaryResolver() {
        return WebDriverBinaryResolver.of(Path.of(stringProcess("${[config] perfeccionista.browser.edge.webdriver}")));
    }

}

