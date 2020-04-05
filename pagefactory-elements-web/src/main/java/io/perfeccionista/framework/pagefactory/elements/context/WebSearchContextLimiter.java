package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.WebParentElement;

public interface WebSearchContextLimiter<T extends WebParentElement> extends SearchContextLimiter<T> {

    T getContext();

}
