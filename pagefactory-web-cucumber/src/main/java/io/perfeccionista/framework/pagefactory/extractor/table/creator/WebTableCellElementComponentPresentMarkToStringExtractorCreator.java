package io.perfeccionista.framework.pagefactory.extractor.table.creator;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementComponentPresentMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebTableCellElementComponentPresentMarkToStringExtractorCreator implements WebTableValueExtractorCreator {

    private final String componentName;

    public WebTableCellElementComponentPresentMarkToStringExtractorCreator(@NotNull String componentName) {
        this.componentName = componentName;
    }

    @Override
    public @NotNull WebTableValueExtractor<String> createForElement(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementComponentPresentMarkToStringExtractor(columnName, elementPath, componentName);
    }

}
