package io.perfeccionista.framework.pagefactory.browser.dispatcher;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.browser.type.WebDriverType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.platform.commons.util.ReflectionUtils.newInstance;

public class WdmWebBrowserSeleniumDispatcher<T extends RemoteWebDriver, C extends MutableCapabilities> extends AbstractWebBrowserSeleniumDispatcher<T, C> {

    public WdmWebBrowserSeleniumDispatcher(Environment environment, WebDriverType<T, C> webDriverType) {
        super(environment, webDriverType);
    }

    @Override
    public WebBrowserDispatcher launch() {
        WebDriverManager.getInstance(this.webDriverType.getWebDriverClass()).setup();
        this.instance = newInstance(this.webDriverType.getWebDriverClass(), this.options);
        setTimeouts();
        return this;
    }

}
