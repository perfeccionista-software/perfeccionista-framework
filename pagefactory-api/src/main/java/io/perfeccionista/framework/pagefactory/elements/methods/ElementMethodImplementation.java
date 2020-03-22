package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;

/**
 * TODO JavaDoc
 */
public interface ElementMethodImplementation<E extends ChildElement, T> {

    T execute(E element, Object... args);

}
