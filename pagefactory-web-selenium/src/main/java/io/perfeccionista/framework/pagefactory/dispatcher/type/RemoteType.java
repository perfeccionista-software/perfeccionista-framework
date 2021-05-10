package io.perfeccionista.framework.pagefactory.dispatcher.type;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteType implements WebDriverType<RemoteWebDriver, MutableCapabilities> {

    @Override
    public Class<RemoteWebDriver> getWebDriverClass() {
        return RemoteWebDriver.class;
    }

    @Override
    public String getLinkedProperty() {
        return null;
    }

    @Override
    public DesiredCapabilities getDefaultCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "normal");
        return desiredCapabilities;
    }

}
