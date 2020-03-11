package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.context.MobileSearchLimiterRegistry;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface MobilePage extends MobileParentElement {

    MobileSearchLimiterRegistry getSearchLimiterRegistry();

    ElementsConfiguration getElementsConfiguration();

    OperationResult<Boolean> isPageOpen();

}
