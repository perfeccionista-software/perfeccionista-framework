package io.perfeccionista.framework.bdd.extractors.table;

import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementSelectedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebTableCellElementSelectedMarkToStringExtractor implements WebTableValueExtractor<String> {

    private TableSection section = TableSection.BODY;

    protected final String columnName;
    protected final String elementName;

    public WebTableCellElementSelectedMarkToStringExtractor(@NotNull String columnName,
                                                            @NotNull String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebTableFilter filter) {
        WebTableCellElementSelectedMarkExtractor extractor = new WebTableCellElementSelectedMarkExtractor(columnName, elementName);
        if (TableSection.HEADER == section) {
            extractor.fromHeader();
        }
        if (TableSection.FOOTER == section) {
            extractor.fromFooter();
        }
        return extractor.extractValues(filter).entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() ? "1" : "0"));
    }

    @Override
    public WebTableCellElementSelectedMarkToStringExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public WebTableCellElementSelectedMarkToStringExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
