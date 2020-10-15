package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class WebTableRowExtractor implements WebTableCellValueExtractor<WebBlock> {

    private TableSection section = TableSection.BODY;

    @Override
    public Map<Integer, WebBlock> extractValues(@NotNull WebTableFilter filter) {
        WebFilterResult filterResult = filter.getFilterResult();
        WebTable element = filter.getElement();

        WebPageFactory webPageFactory = element.getEnvironment()
                .getService(WebPageService.class)
                .getWebPageFactory();

        return webPageFactory.createWebTableRows(element, section, filterResult);
    }

    @Override
    public WebTableRowExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public WebTableRowExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
