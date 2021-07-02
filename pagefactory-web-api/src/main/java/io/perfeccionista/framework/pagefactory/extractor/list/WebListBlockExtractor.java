package io.perfeccionista.framework.pagefactory.extractor.list;

import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebListBlockExtractor<R extends WebBlock, T extends WebBlock> implements WebListBlockValueExtractor<R, T> {

    private final Class<R> blockClass;

    public WebListBlockExtractor(@NotNull Class<R> blockClass) {
        this.blockClass = blockClass;
    }

    @Override
    public Map<Integer, R> extractValues(@NotNull WebListFilter<T> filter) {
        FilterResult filterResult = filter.getFilterResult();
        WebList<T> element = filter.getElement();

        WebPageFactory webPageFactory = element.getEnvironment()
                .getService(WebPageService.class)
                .getWebPageFactory();

        return webPageFactory.createWebListBlocks(element, filterResult)
                .entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> (R) entry.getValue()));
    }

}
