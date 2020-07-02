package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

import java.util.stream.Stream;

public class WebTableRowContextLimiter implements WebSearchContextLimiter<WebMappedBlock> {

    public WebTableRowContextLimiter(WebTable element, WebTableFilter filter) {

    }

    public WebTableRowContextLimiter(String elementPath, WebTableFilter filter) {

    }

    @Override
    public WebSearchContextLimiter<WebMappedBlock> setParentContext(WebSearchContextLimiter<? extends WebParentElement> parentContext) {
        return null;
    }

    @Override
    public WebSearchContextLimiter<WebMappedBlock> resetCachedElement() {
        return null;
    }

    @Override
    public Stream<WebMappedBlock> getContexts() {
        return null;
    }

    @Override
    public WebMappedBlock getContext() {
        return null;
    }

}
