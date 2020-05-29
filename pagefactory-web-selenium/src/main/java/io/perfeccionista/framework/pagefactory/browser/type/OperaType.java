package io.perfeccionista.framework.pagefactory.browser.type;

import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class OperaType implements WebDriverType<OperaDriver, OperaOptions> {

    @Override
    public Class<OperaDriver> getWebDriverClass() {
        return OperaDriver.class;
    }

    @Override
    public String getLinkedProperty() {
        return "webdriver.opera.driver";
    }

    @Override
    public OperaOptions getDefaultCapabilities() {
        return new OperaOptions();
    }

}
