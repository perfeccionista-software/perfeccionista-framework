package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface WebItemValueExtractor<R, T extends WebBlock<?>> {

    /**
     * Если индексы пустые, то ищем по всем
     * @return
     */
    Map<Integer, R> extractValues(@NotNull WebListFilter<T> filter);

}
