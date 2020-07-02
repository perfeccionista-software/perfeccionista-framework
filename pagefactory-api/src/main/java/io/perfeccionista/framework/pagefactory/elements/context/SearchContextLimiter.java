package io.perfeccionista.framework.pagefactory.elements.context;

import java.util.stream.Stream;

public interface SearchContextLimiter<T> {

    Stream<T> getContexts();

    T getContext();

}
