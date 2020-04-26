package io.perfeccionista.framework.pagefactory.elements.interactions;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;

public interface ElementInteractionImplementation<S extends ChildElement<?>, T extends ChildElement<?>, R> {

    R execute(S sourceElement, T targetElement, Object... args);

}
