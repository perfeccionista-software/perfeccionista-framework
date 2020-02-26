package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.ElementsConfiguration;
import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

/**
 *
 */
public interface Page<T extends ChildElement<?>> extends ParentElement<T> {

    ElementsConfiguration getElementsConfiguration();

    OperationResult<Boolean> isPageOpen();

}
