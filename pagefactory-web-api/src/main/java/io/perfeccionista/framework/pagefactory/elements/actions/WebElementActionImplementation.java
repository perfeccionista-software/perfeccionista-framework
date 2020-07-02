package io.perfeccionista.framework.pagefactory.elements.actions;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

public interface WebElementActionImplementation<T> {

    T execute(WebChildElement element, Object... args);

}
