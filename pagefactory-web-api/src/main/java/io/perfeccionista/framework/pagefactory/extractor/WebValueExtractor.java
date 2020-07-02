package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;

public interface WebValueExtractor<T extends WebChildElement, R extends FilterResult, V> {

    /**
     * Если индексы пустые, то ищем по всем
     * @return
     */
    MultipleResult<V> extractValues(T element, R filterResult);

}
