package io.perfeccionista.framework.pagefactory.extractor.texttable;

import io.perfeccionista.framework.pagefactory.WebPageService;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.factory.WebPageFactory;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class WebTextTableCellElementExtractor implements WebTextTableValueExtractor<WebLink> {

    private TableSection section = TableSection.BODY;

    private final String columnName;

    public WebTextTableCellElementExtractor(@NotNull String columnName) {
        this.columnName = columnName;
    }

    @Override
    public Map<Integer, WebLink> extractValues(@NotNull WebTextTableFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        WebTextTable element = filter.getElement();

        Map<Integer, WebLink> extractedElements = new HashMap<>();

        WebPageFactory webPageFactory = element.getEnvironment()
                .getService(WebPageService.class)
                .getWebPageFactory();

        webPageFactory.createWebTextTableCells(element, columnName, section, filterResult)
                .forEach((index, webMappedBlock) -> extractedElements.put(index, webMappedBlock.textLink()));

        return extractedElements;
    }

    @Override
    public WebTextTableCellElementExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public WebTextTableCellElementExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
