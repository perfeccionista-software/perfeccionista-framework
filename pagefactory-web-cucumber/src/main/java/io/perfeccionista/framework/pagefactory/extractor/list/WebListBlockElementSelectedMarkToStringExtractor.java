package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebListBlockElementSelectedMarkToStringExtractor implements WebListBlockValueExtractor<String> {

    private final String elementName;

    public WebListBlockElementSelectedMarkToStringExtractor(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebListFilter filter) {
        return new WebListBlockElementSelectedMarkExtractor(elementName)
                .extractValues(filter).entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() ? "1" : "0"));
    }

}
