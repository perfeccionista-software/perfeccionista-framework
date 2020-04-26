package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.WebParentElement;

import java.util.stream.Stream;

public interface WebSearchContextLimiter<T extends WebParentElement> extends SearchContextLimiter<T> {

    WebSearchContextLimiter<T> setParentContext(WebSearchContextLimiter<? extends WebParentElement> parentContext);

    WebSearchContextLimiter<T> resetCachedElement();

    @Override
    Stream<T> getContexts();

    @Override
    T getContext();

}
