package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebBlockElementComponentDisplayedMarkToStringExtractor implements WebBlockValueExtractor<String, WebBlock> {

    private final String elementPath;
    private final String componentName;

    public WebBlockElementComponentDisplayedMarkToStringExtractor(@NotNull String elementPath,
                                                                  @NotNull String componentName) {
        this.elementPath = elementPath;
        this.componentName = componentName;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebBlockFilter<WebBlock> filter) {
        return new WebBlockElementComponentDisplayedMarkExtractor<>(elementPath, componentName)
                .extractValues(filter).entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() ? "1" : "0"));
    }

}
