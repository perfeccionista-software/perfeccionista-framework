package io.perfeccionista.framework.pagefactory.dispatcher.configuration.selenium;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.configuration.WebBrowserBinaryResolver;
import io.perfeccionista.framework.pagefactory.dispatcher.LocalWebBrowserSeleniumDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.type.EdgeType;
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

    protected WebBrowserBinaryResolver getWebDriverBinaryResolver() {
        return WebBrowserBinaryResolver.of(Path.of(stringProcess("${[config] perfeccionista.browser.edge.webdriver}")));
    }

}

