package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.browser.MobileDriverDispatcher;
import io.perfeccionista.framework.pagefactory.elements.methods.SwipeToAvailable;
import io.perfeccionista.framework.pagefactory.elements.actions.MobileElementActionImplementation;
import io.perfeccionista.framework.pagefactory.elements.properties.MobileElementPropertyAvailable;
import io.perfeccionista.framework.pagefactory.elements.properties.MobileElementPropertyHolder;

public interface MobileChildElement extends MobileElementPropertyAvailable<MobileElementPropertyHolder>, SwipeToAvailable {

    <T> MobileElementActionImplementation<T> getMethodImplementation(String methodType, Class<T> returnType);

    MobileDriverDispatcher getDriverInstance();

//    MultipleResult<MobileElement> findElements(LocatorChain locatorChain);
//
//    SingleResult<MobileElement> findElement(LocatorChain locatorChain);

}
