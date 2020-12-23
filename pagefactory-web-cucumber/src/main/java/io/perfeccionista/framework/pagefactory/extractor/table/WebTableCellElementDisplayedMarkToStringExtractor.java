package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebTableCellElementDisplayedMarkToStringExtractor implements WebTableValueExtractor<String> {

    private TableSection section = TableSection.BODY;

    private final String columnName;
    private final String elementPath;

    public WebTableCellElementDisplayedMarkToStringExtractor(@NotNull String columnName,
                                                             @NotNull String elementPath) {
        this.columnName = columnName;
        this.elementPath = elementPath;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebTableFilter filter) {
        WebTableCellElementDisplayedMarkExtractor extractor =
                new WebTableCellElementDisplayedMarkExtractor(columnName, elementPath);
        if (TableSection.BODY == section) {
            return extractor
                    .extractValues(filter).entrySet().stream()
                    .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() ? "1" : "0"));
        } else if (TableSection.HEADER == section) {
            return extractor.fromHeader()
                    .extractValues(filter).entrySet().stream()
                    .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() ? "1" : "0"));
        } else {
            return extractor.fromFooter()
                    .extractValues(filter).entrySet().stream()
                    .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() ? "1" : "0"));
        }
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
