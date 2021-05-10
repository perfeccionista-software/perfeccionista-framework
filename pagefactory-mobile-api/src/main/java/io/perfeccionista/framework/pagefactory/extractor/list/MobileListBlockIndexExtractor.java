package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.filter.list.MobileListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class MobileListBlockIndexExtractor implements MobileListBlockValueExtractor<Integer> {

    @Override
    public Map<Integer, Integer> extractValues(@NotNull MobileListFilter filter) {
        return filter.getFilterResult()
                .getIndexes().stream()
                .collect(toMap(index -> index, index -> index));
    }

}
