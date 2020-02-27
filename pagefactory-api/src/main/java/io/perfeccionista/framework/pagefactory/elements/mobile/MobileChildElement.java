package io.perfeccionista.framework.pagefactory.elements.mobile;

import io.appium.java_client.AppiumDriver;
import io.perfeccionista.framework.pagefactory.driver.DriverInstance;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SwipeToAvailable;
import io.perfeccionista.framework.pagefactory.elements.mobile.methods.MobileElementMethodImplementation;
import org.openqa.selenium.SearchContext;

public interface MobileChildElement extends ChildElement<MobileParentElement>, SwipeToAvailable {

    <T> MobileElementMethodImplementation<T> getMethodImplementation(String methodType, Class<T> returnType);

    DriverInstance<AppiumDriver<?>> getDriverInstance();

    /**
     * Возвращает контекст поиска для родительского элемента
     * Маппер в реализации метода
     * @return
     */
    SearchContext getSearchContext();

}
