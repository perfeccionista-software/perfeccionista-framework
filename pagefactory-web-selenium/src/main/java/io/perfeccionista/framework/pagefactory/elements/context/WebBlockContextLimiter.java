package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;

import java.util.stream.Stream;

public class WebBlockContextLimiter<T extends WebBlock> implements WebSearchContextLimiter<T> {

    private final String elementName;
    private final WebBlock element;

    public WebBlockContextLimiter(String elementName) {
        this.elementName = elementName;
        this.element = null;
    }

    public WebBlockContextLimiter(WebBlock element) {
        this.elementName = null;
        this.element = element;
    }

    @Override
    public WebSearchContextLimiter<T> setParentContext(WebSearchContextLimiter<? extends WebParentElement> parentContext) {
        return null;
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
