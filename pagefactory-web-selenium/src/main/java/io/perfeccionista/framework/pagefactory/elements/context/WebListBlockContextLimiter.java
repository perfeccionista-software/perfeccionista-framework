package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;

import java.util.stream.Stream;

public class WebListBlockContextLimiter<T extends WebMappedBlock> implements WebSearchContextLimiter<T> {

    public WebListBlockContextLimiter(WebList element, WebListFilter filter) {

    }

    public WebListBlockContextLimiter(String elementPath, WebListFilter filter) {

    }

    @Override
    public WebSearchContextLimiter<T> setParentContext(WebSearchContextLimiter<? extends WebParentElement> parentContext) {

        return this;
    }

    @Override
    public WebSearchContextLimiter<T> resetCachedElement() {
        return null;
    }

    @Override
    public Stream<T> getContexts() {
        return null;
    }

    @Override
    public T getContext() {
        return null;
    }

}
