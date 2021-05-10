package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.pagefactory.filter.textlist.MobileTextListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class MobileTextListBlockIndexExtractor implements MobileTextListBlockValueExtractor<Integer> {

    @Override
    public Map<Integer, Integer> extractValues(@NotNull MobileTextListFilter filter) {
        return filter.getFilterResult()
                .getIndexes().stream()
                .collect(toMap(index -> index, index -> index));
    }

}
