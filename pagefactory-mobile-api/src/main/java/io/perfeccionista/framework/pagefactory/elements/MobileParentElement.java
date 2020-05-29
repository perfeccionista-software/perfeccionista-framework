package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.browser.MobileDriverDispatcher;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;

public interface MobileParentElement extends ParentElement<MobileChildElement> {

    MobileDriverDispatcher getDriverInstance();

    MobileElementRegistry getElementRegistry();


}
