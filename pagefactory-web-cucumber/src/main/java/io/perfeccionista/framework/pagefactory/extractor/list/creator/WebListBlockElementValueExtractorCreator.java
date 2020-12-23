package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import org.jetbrains.annotations.NotNull;

public interface WebListBlockElementValueExtractorCreator {

    @NotNull WebListBlockValueExtractor<String> createForElement(@NotNull String blockElementName);

}
