package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;

public interface WebValueExtractor<T extends WebChildElement, F extends WebFilter, V> {

    /**
     * Если индексы пустые, то ищем по всем
     * @return
     */
    MultipleResult<V> extractValues(T element, F filter);

}
