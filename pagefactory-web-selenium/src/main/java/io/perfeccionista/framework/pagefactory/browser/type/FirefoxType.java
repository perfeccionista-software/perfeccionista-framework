package io.perfeccionista.framework.pagefactory.browser.type;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxType implements WebDriverType<FirefoxDriver, FirefoxOptions> {

    @Override
    public Class<FirefoxDriver> getWebDriverClass() {
        return FirefoxDriver.class;
    }

    @Override
    public String getLinkedProperty() {
        return "webdriver.gecko.driver";
    }

    @Override
    public FirefoxOptions getDefaultCapabilities() {
        return new FirefoxOptions();
    }
}
