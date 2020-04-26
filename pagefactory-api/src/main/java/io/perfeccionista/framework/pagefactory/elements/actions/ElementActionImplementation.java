package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;

/**
 * TODO JavaDoc
 */
public interface ElementActionImplementation<S extends ChildElement<?>, R> {

    R execute(S element, Object... args);

}
