package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.base.ParentElement;

import java.util.stream.Stream;

public interface SearchContextLimiter<T extends ParentElement<?>> {

    Stream<T> getContexts();

    T getContext();

}
