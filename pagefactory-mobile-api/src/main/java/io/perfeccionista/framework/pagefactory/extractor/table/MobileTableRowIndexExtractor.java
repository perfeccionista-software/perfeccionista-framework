package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.filter.table.MobileTableFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class MobileTableRowIndexExtractor implements MobileTableValueExtractor<Integer> {

    private TableSection section = TableSection.BODY;

    @Override
    public Map<Integer, Integer> extractValues(@NotNull MobileTableFilter filter) {
        if (TableSection.FOOTER == section || TableSection.HEADER == section) {
            HashMap<Integer, Integer> result = new HashMap<>();
            result.put(-1, -1);
            return result;
        }

        return filter.getFilterResult()
                .getIndexes().stream()
                .collect(toMap(index -> index, index -> index));
    }

    @Override
    public MobileTableRowIndexExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public MobileTableRowIndexExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
