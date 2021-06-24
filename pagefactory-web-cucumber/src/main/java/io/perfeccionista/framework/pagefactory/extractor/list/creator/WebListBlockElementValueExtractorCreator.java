package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import org.jetbrains.annotations.NotNull;

public interface WebListBlockElementValueExtractorCreator {

    @NotNull WebListBlockValueExtractor<String, WebBlock> createForElement(@NotNull String blockElementName);

}
