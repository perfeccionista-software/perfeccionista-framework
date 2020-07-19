package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebParentElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;

import java.util.stream.Stream;

public class WebTableCellContextLimiter<T extends WebMappedBlock> implements WebSearchContextLimiter<T> {

    public WebTableCellContextLimiter(WebTable element, String columnName, WebTableFilterBuilder filter) {

    }

    public WebTableCellContextLimiter(String elementPath, String columnName, WebTableFilterBuilder filter) {

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
