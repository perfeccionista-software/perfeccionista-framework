package io.perfeccionista.framework.pagefactory.elements.mobile;

import io.appium.java_client.AppiumDriver;
import io.perfeccionista.framework.pagefactory.driver.DriverInstance;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import org.openqa.selenium.SearchContext;

public interface MobileParentElement extends ParentElement<MobileChildElement> {

    DriverInstance<AppiumDriver<?>> getDriverInstance();

    /**
     * Возвращает контекст поиска для родительского элемента
     * Маппер в реализации метода
     * @return
     */
    SearchContext getSearchContext();
}
