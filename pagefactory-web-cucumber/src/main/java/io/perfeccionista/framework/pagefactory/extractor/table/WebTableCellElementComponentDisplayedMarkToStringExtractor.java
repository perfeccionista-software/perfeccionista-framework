package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.base.TableSection;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebTableCellElementComponentDisplayedMarkToStringExtractor implements WebTableValueExtractor<String> {

    private TableSection section = TableSection.BODY;

    private final String columnName;
    private final String elementPath;
    private final String componentName;

    public WebTableCellElementComponentDisplayedMarkToStringExtractor(@NotNull String columnName,
                                                                      @NotNull String elementPath,
                                                                      @NotNull String componentName) {
        this.columnName = columnName;
        this.elementPath = elementPath;
        this.componentName = componentName;
    }

    @Override
    public Map<Integer, String> extractValues(@NotNull WebTableFilter filter) {
        WebTableCellElementComponentDisplayedMarkExtractor extractor =
                new WebTableCellElementComponentDisplayedMarkExtractor(columnName, elementPath, componentName);
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
    public WebTableCellElementComponentDisplayedMarkToStringExtractor fromHeader() {
        this.section = TableSection.HEADER;
        return this;
    }

    @Override
    public WebTableCellElementComponentDisplayedMarkToStringExtractor fromFooter() {
        this.section = TableSection.FOOTER;
        return this;
    }

}
