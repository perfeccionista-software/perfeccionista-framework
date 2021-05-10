package io.perfeccionista.framework.pagefactory.dispatcher.type;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariType implements WebDriverType<SafariDriver, SafariOptions> {

    @Override
    public Class<SafariDriver> getWebDriverClass() {
        return SafariDriver.class;
    }

    @Override
    public String getLinkedProperty() {
        return "webdriver.safari.driver";
    }

    @Override
    public SafariOptions getDefaultCapabilities() {
        return new SafariOptions();
    }

}
