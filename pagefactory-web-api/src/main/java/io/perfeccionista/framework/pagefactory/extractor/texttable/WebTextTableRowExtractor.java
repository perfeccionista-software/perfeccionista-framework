package io.perfeccionista.framework.pagefactory.extractor.texttable;

import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class WebTextTableRowExtractor implements WebTextTableValueExtractor<WebBlock> {

    private TableSection section = TableSection.BODY;

    @Override
    public Map<Integer, WebBlock> extractValues(@NotNull WebTextTableFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        WebTextTable element = filter.getElement();

        WebPageFactory webPageFactory = element.getEnvironment()
                .getService(WebPageService.class)
                .getWebPageFactory();

        return webPageFactory.createWebTextTableRows(element, section, filterResult);
    }

    @Override
    public WebTextTableRowExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public WebTextTableRowExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
