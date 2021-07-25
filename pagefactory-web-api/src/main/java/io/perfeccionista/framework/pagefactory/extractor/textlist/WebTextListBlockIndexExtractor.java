package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.pagefactory.filter.textblock.WebTextBlockFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class WebTextListBlockIndexExtractor implements WebTextListBlockValueExtractor<Integer> {

    @Override
    public Map<Integer, Integer> extractValues(@NotNull WebTextBlockFilter filter) {
        return filter.getFilterResult()
                .getIndexes().stream()
                .collect(toMap(index -> index, index -> index));
    }

}
