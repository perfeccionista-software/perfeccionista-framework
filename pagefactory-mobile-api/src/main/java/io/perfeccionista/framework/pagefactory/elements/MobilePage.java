package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.context.MobileSearchLimiterRegistry;

public interface MobilePage extends MobileParentElement {

    MobileSearchLimiterRegistry getSearchLimiterRegistry();

//    ElementsConfiguration getElementsConfiguration();

//    OperationResult<Boolean> isPageOpen();

}
