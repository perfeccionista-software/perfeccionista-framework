package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebListBlockElementPresentMarkToStringExtractor implements WebListBlockValueExtractor<String, WebBlock> {

    private final String elementName;

    public WebListBlockElementPresentMarkToStringExtractor(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebListFilter<WebBlock> filter) {
        return new WebListBlockElementPresentMarkExtractor<>(elementName)
                .extractValues(filter).entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() ? "1" : "0"));
    }

}
