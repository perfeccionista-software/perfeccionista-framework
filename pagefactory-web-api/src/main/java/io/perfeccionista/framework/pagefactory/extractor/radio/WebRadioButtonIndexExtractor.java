package io.perfeccionista.framework.pagefactory.extractor.radio;

import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioGroupFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class WebRadioButtonIndexExtractor implements WebRadioButtonValueExtractor<Integer> {

    @Override
    public Map<Integer, Integer> extractValues(@NotNull WebRadioGroupFilter filter) {
        return filter.getFilterResult()
                .getIndexes().stream()
                .collect(toMap(index -> index, index -> index));
    }

}
