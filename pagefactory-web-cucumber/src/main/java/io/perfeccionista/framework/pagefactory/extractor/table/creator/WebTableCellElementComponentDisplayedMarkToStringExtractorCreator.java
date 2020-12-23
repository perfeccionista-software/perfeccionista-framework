package io.perfeccionista.framework.pagefactory.extractor.table.creator;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementComponentDisplayedMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebTableCellElementComponentDisplayedMarkToStringExtractorCreator implements WebTableValueExtractorCreator {

    private final String componentName;

    public WebTableCellElementComponentDisplayedMarkToStringExtractorCreator(@NotNull String componentName) {
        this.componentName = componentName;
    }

    @Override
    public @NotNull WebTableValueExtractor<String> createForElement(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementComponentDisplayedMarkToStringExtractor(columnName, elementPath, componentName);
    }

}
