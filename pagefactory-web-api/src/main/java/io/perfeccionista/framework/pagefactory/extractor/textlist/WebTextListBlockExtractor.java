package io.perfeccionista.framework.pagefactory.extractor.textlist;

import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.DefaultWebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class WebTextListBlockExtractor implements WebTextListBlockValueExtractor<DefaultWebTextBlock> {

    @Override
    public Map<Integer, DefaultWebTextBlock> extractValues(@NotNull WebTextListFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        WebTextList element = filter.getElement();

        WebPageFactory webPageFactory = element.getEnvironment()
                .getService(WebPageService.class)
                .getWebPageFactory();

        return webPageFactory.createWebTextListBlocks(element, filterResult);
    }

}
