package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.MobilePageService;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.factory.MobilePageFactory;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MobileTableCellExtractor<T extends MobileBlock> implements MobileTableValueExtractor<T> {

    private TableSection section = TableSection.BODY;

    private final String columnName;
    private final Class<T> blockClass;

    public MobileTableCellExtractor(@NotNull String columnName, @NotNull Class<T> blockClass) {
        this.columnName = columnName;
        this.blockClass = blockClass;
    }

    @Override
    public Map<Integer, T> extractValues(@NotNull MobileTableFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        MobileTable element = filter.getElement();

        MobilePageFactory webPageFactory = element.getEnvironment()
                .getService(MobilePageService.class)
                .getMobilePageFactory();

        return webPageFactory
                .createMobileTableCells(element, columnName, section, filterResult)
                .entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> (T) entry.getValue()));
    }

    @Override
    public MobileTableCellExtractor<T> fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public MobileTableCellExtractor<T> fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
