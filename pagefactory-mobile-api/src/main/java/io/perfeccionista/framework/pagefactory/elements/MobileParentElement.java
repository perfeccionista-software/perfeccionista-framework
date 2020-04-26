package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.browser.MobileDriverInstance;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;

public interface MobileParentElement extends ParentElement<MobileChildElement> {

    MobileDriverInstance getDriverInstance();

    MobileElementRegistry getElementRegistry();


}
