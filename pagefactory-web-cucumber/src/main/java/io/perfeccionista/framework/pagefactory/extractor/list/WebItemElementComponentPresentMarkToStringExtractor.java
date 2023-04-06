package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementComponentPresentMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebItemElementComponentPresentMarkToStringExtractor
//        implements WebItemValueExtractor<String, WebBlock>
{

    private final String elementPath;
    private final String componentName;

    public WebItemElementComponentPresentMarkToStringExtractor(@NotNull String elementPath,
                                                               @NotNull String componentName) {
        this.elementPath = elementPath;
        this.componentName = componentName;
    }

//    @Override
//    public Map<Integer, String> extractValues(@NotNull WebBlockFilter<WebBlock> filter) {
//        return new WebItemElementComponentPresentMarkExtractor<>(elementPath, componentName)
//                .extractValues(filter).entrySet().stream()
//                .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() ? "1" : "0"));
//    }

}
