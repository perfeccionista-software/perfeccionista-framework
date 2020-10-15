package io.perfeccionista.framework.bdd.extractors.table;

import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementDisplayedMarkExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebTableCellElementDisplayedMarkToStringExtractor implements WebTableCellValueExtractor<String> {

    private TableSection section = TableSection.BODY;

    protected final String columnName;
    protected final String elementName;

    public WebTableCellElementDisplayedMarkToStringExtractor(@NotNull String columnName,
                                                             @NotNull String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebTableFilter filter) {
        WebTableCellElementDisplayedMarkExtractor extractor = new WebTableCellElementDisplayedMarkExtractor(columnName, elementName);
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
    public WebTableCellElementDisplayedMarkToStringExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public WebTableCellElementDisplayedMarkToStringExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
