package io.perfeccionista.framework.pagefactory.elements.mobile;

import io.perfeccionista.framework.pagefactory.elements.registry.MobileElementRegistry;

public interface MobileBlock extends MobileChildElement, MobileParentElement {

    MobileElementRegistry getMobileElementRegistry();

    int getIndex();

}
