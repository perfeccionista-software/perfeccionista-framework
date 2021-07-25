package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockElementSelectedMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebBlockValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebListBlockElementSelectedMarkToStringExtractorCreator implements WebListBlockElementValueExtractorCreator {

    @Override
    public @NotNull WebBlockValueExtractor<String, WebBlock> createForElement(@NotNull String blockElementName) {
        return new WebBlockElementSelectedMarkToStringExtractor(blockElementName);
    }

}
