package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.MobilePageService;
import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.elements.MobileTableRow;
import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.factory.MobilePageFactory;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MobileTableRowExtractor implements MobileTableValueExtractor<MobileTableRow> {

    private TableSection section = TableSection.BODY;

    @Override
    public Map<Integer, MobileTableRow> extractValues(@NotNull MobileTableFilter filter) {
        FilterResult filterResult = filter.getFilterResult();
        MobileTable element = filter.getElement();

        MobilePageFactory webPageFactory = element.getEnvironment()
                .getService(MobilePageService.class)
                .getMobilePageFactory();

        return webPageFactory.createMobileTableRows(element, section, filterResult);
    }

    @Override
    public MobileTableRowExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public MobileTableRowExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
