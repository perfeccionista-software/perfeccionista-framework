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
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebTableCellExtractor<T extends WebBlock> implements WebTableCellValueExtractor<T> {

    private TableSection section = TableSection.BODY;

    private final String columnName;
    private final Class<T> blockClass;

    public WebTableCellExtractor(@NotNull String columnName, @NotNull Class<T> blockClass) {
        this.columnName = columnName;
        this.blockClass = blockClass;
    }

    @Override
    public Map<Integer, T> extractValues(@NotNull WebTableFilter filter) {
        WebFilterResult filterResult = filter.getFilterResult();
        WebTable element = filter.getElement();

        WebPageFactory webPageFactory = element.getEnvironment()
                .getService(WebPageService.class)
                .getWebPageFactory();

        return webPageFactory
                .createWebTableCells(element, columnName, section, filterResult)
                .entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> (T) entry.getValue()));
    }

    @Override
    public WebTableCellExtractor<T> fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public WebTableCellExtractor<T> fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}