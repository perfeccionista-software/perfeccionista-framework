package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.filter.MobileFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface MobileValueExtractor<T extends MobileChildElement, F extends MobileFilter<T>, V> {

    /**
     * Если индексы пустые, то ищем по всем
     * @return
     */
    Map<Integer, V> extractValues(@NotNull F filter);

}
