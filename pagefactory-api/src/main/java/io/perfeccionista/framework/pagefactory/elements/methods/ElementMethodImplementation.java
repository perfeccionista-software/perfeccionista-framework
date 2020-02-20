package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

/**
 * TODO JavaDoc
 */
public interface ElementMethodImplementation<T> {

    OperationResult<T> execute(ChildElement element, Object... args);

}
