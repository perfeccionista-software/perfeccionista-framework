package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static java.util.stream.Collectors.*;

public class WebListBlockIndexExtractor implements WebListBlockValueExtractor<Integer> {

    @Override
    public Map<Integer, Integer> extractValues(@NotNull WebListFilter filter) {
        return filter.getFilterResult()
                .getIndexes().stream()
                .collect(toMap(index -> index, index -> index));
    }

}
