package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

/**
 *
 */
public interface Page extends ParentElement {

    ElementsConfiguration getElementsConfiguration();

    OperationResult<Boolean> isPageOpen();

}
