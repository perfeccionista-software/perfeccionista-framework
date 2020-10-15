package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;

import java.util.stream.Stream;

public class WebTableRowContextLimiter implements WebSearchContextLimiter<WebBlock> {

    public WebTableRowContextLimiter(WebTable element, WebTableFilterBuilder filter) {

    }

    public WebTableRowContextLimiter(String elementPath, WebTableFilterBuilder filter) {

    }

    @Override
    public WebSearchContextLimiter<WebBlock> setParentContext(WebSearchContextLimiter<? extends WebParentElement> parentContext) {
        return null;
    }

    @Override
    public WebSearchContextLimiter<WebBlock> resetCachedElement() {
        return null;
    }

    @Override
    public Stream<WebBlock> getContexts() {
        return null;
    }

    @Override
    public WebBlock getContext() {
        return null;
    }

}
