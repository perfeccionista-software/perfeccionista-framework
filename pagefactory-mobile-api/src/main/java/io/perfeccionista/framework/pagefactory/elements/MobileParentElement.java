package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.browser.MobileDriverDispatcher;
import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;

public interface MobileParentElement  {

    MobileDriverDispatcher getDriverInstance();

    MobileElementRegistry getElementRegistry();


}
