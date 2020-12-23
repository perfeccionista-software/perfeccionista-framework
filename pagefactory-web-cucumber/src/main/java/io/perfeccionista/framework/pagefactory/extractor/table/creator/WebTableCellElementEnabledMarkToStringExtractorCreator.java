package io.perfeccionista.framework.pagefactory.extractor.table.creator;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementEnabledMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebTableCellElementEnabledMarkToStringExtractorCreator implements WebTableValueExtractorCreator {

    @Override
    public @NotNull WebTableValueExtractor<String> createForElement(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementEnabledMarkToStringExtractor(columnName, elementPath);
    }

}
