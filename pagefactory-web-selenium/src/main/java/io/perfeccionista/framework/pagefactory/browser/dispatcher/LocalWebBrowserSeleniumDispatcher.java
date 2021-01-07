package io.perfeccionista.framework.pagefactory.browser.dispatcher;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.SeleniumWebDriverBinaryNotDeclared;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.configuration.WebDriverBinaryResolver;
import io.perfeccionista.framework.pagefactory.browser.type.WebDriverType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.nio.file.Path;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.WEB_DRIVER_BINARY_NOT_DECLARED;
import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class LocalWebBrowserSeleniumDispatcher<T extends RemoteWebDriver, C extends MutableCapabilities> extends AbstractWebBrowserSeleniumDispatcher<T, C> {

    protected WebDriverBinaryResolver binaryResolver;

    public LocalWebBrowserSeleniumDispatcher(Environment environment, WebDriverType<T, C> webDriverType, WebDriverBinaryResolver binaryResolver) {
        super(environment, webDriverType);
        this.binaryResolver = binaryResolver;
    }

    @Override
    public WebBrowserDispatcher launch() {
        Path localFilePath = this.binaryResolver.getPath()
                .orElseThrow(() -> SeleniumWebDriverBinaryNotDeclared.exception(WEB_DRIVER_BINARY_NOT_DECLARED.getMessage(System.getProperty("os.name"))));
        System.setProperty(webDriverType.getLinkedProperty(), localFilePath.toString());
        this.instance = newInstance(this.webDriverType.getWebDriverClass(), this.options);
        setTimeouts();

        // TODO: check started

        return this;
    }

}
