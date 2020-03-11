package io.perfeccionista.framework.pagefactory.elements;

import io.appium.java_client.MobileElement;
import io.perfeccionista.framework.pagefactory.driver.MobileDriverInstance;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SwipeToAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MobileElementMethodImplementation;
import io.perfeccionista.framework.pagefactory.elements.properties.ElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.MobileElementPropertyHolder;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public interface MobileChildElement extends ChildElement<MobileParentElement>,
        ElementPropertyAvailable<MobileElementPropertyHolder>, SwipeToAvailable {

    <T> MobileElementMethodImplementation<T> getMethodImplementation(String methodType, Class<T> returnType);

    MobileDriverInstance getDriverInstance();

    MultipleResult<MobileElement> findElements(LocatorChain locatorChain);

    SingleResult<MobileElement> findElement(LocatorChain locatorChain);

}
