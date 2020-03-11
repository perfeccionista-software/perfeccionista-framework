package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.MobileParentElement;

public interface MobileSearchContextLimiter extends SearchContextLimiter<MobileParentElement> {

    MobileParentElement getContext();

}
