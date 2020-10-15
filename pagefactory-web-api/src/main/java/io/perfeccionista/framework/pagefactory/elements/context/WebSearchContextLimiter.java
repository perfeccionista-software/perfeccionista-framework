package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;

import java.util.stream.Stream;

public interface WebSearchContextLimiter<T extends WebParentElement> {

    WebSearchContextLimiter<T> setParentContext(WebSearchContextLimiter<? extends WebParentElement> parentContext);

    WebSearchContextLimiter<T> resetCachedElement();

    Stream<T> getContexts();

    T getContext();

}
