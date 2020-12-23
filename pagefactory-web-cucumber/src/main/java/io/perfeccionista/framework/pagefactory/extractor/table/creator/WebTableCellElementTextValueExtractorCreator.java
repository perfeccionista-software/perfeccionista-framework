package io.perfeccionista.framework.pagefactory.extractor.table.creator;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellElementTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebTableCellElementTextValueExtractorCreator implements WebTableValueExtractorCreator {

    @Override
    public @NotNull WebTableValueExtractor<String> createForElement(@NotNull String columnName, @NotNull String elementPath) {
        return new WebTableCellElementTextValueExtractor(columnName, elementPath);
    }

}
