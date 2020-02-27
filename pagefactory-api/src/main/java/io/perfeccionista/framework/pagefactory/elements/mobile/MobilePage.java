package io.perfeccionista.framework.pagefactory.elements.mobile;

import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface MobilePage extends MobileParentElement {

    ElementsConfiguration getElementsConfiguration();

    OperationResult<Boolean> isPageOpen();

}
