package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebItemElementSelectedMarkToStringExtractor
//        implements WebItemValueExtractor<String, WebBlock>
{

    private final String elementName;

    public WebItemElementSelectedMarkToStringExtractor(String elementName) {
        this.elementName = elementName;
    }

//    @Override
//    public Map<Integer, String> extractValues(@NotNull WebBlockFilter<WebBlock> filter) {
//        return new WebItemElementSelectedMarkExtractor<>(elementName)
//                .extractValues(filter).entrySet().stream()
//                .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() ? "1" : "0"));
//    }

}
