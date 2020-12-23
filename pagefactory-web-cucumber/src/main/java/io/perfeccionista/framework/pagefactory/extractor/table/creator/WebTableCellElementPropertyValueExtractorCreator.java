package io.perfeccionista.framework.pagefactory.extractor.table.creator;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementPropertyValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebTableCellElementPropertyValueExtractorCreator implements WebTableValueExtractorCreator {

    private final String propertyName;

    public WebTableCellElementPropertyValueExtractorCreator(@NotNull String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public @NotNull WebTableValueExtractor<String> createForElement(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementPropertyValueExtractor(columnName, elementPath, propertyName);
    }

}
