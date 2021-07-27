package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static java.util.stream.Collectors.*;

public class WebBlockIndexExtractor<T extends WebBlock> implements WebBlockValueExtractor<Integer, T> {

    @Override
    public Map<Integer, Integer> extractValues(@NotNull WebBlockFilter<T> filter) {
        return filter.getFilterResult()
                .getIndexes().stream()
                .collect(toMap(index -> index, index -> index));
    }

}
