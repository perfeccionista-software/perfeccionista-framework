package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface WebValueExtractor<T extends WebChildElement, F extends WebFilter<T>, R> {

    /**
     * Если индексы пустые, то ищем по всем
     * @return
     */
    Map<Integer, R> extractValues(@NotNull F filter);

}
