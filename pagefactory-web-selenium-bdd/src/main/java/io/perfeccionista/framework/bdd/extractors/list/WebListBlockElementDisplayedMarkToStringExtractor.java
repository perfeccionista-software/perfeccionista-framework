package io.perfeccionista.framework.bdd.extractors.list;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebListBlockElementDisplayedMarkToStringExtractor implements WebListBlockValueExtractor<String> {

    private final String elementName;

    public WebListBlockElementDisplayedMarkToStringExtractor(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebListFilter filter) {
        return new WebListBlockElementDisplayedMarkExtractor(elementName)
                .extractValues(filter).entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() ? "1" : "0"));
    }

}

