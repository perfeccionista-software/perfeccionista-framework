package io.perfeccionista.framework.pagefactory.dispatcher.type;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public interface WebDriverType<T extends WebDriver, C extends MutableCapabilities> {

    Class<T> getWebDriverClass();

    String getLinkedProperty();

    C getDefaultCapabilities();

}
