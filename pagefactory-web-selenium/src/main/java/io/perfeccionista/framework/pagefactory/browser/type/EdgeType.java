package io.perfeccionista.framework.pagefactory.browser.type;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeType implements WebDriverType<EdgeDriver, EdgeOptions> {

    @Override
    public Class<EdgeDriver> getWebDriverClass() {
        return EdgeDriver.class;
    }

    @Override
    public String getLinkedProperty() {
        return "webdriver.edge.driver";
    }

    @Override
    public EdgeOptions getDefaultCapabilities() {
        return new EdgeOptions();
    }
}

