package io.perfeccionista.framework.pagefactory.extractor.table.creator;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;
import org.jetbrains.annotations.NotNull;

public interface WebTableValueExtractorCreator {

    @NotNull WebTableValueExtractor<String> createForElement(@NotNull String columnName, @NotNull String elementPath);

}
