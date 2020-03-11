package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;

public interface SearchContextLimiter<T extends ParentElement<?>> {

    T getContext();

}
