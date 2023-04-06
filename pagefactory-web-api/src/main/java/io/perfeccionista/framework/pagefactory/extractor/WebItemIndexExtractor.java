package io.perfeccionista.framework.pagefactory.extractor;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static java.util.stream.Collectors.*;

public class WebItemIndexExtractor<T extends WebBlock<?>> implements WebItemValueExtractor<Integer, T> {

    @Override
    public Map<Integer, Integer> extractValues(@NotNull WebListFilter<T> filter) {
        return filter.getFilterResult()
                .getIndexes().stream()
                .collect(toMap(index -> index, index -> index));
    }

}
