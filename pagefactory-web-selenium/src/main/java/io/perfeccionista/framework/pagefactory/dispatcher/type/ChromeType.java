package io.perfeccionista.framework.pagefactory.dispatcher.type;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeType implements WebDriverType<ChromeDriver, ChromeOptions> {

    @Override
    public Class<ChromeDriver> getWebDriverClass() {
        return ChromeDriver.class;
    }

    @Override
    public String getLinkedProperty() {
        return "webdriver.chrome.driver";
    }

    @Override
    public ChromeOptions getDefaultCapabilities() {
        return new ChromeOptions();
    }

}
