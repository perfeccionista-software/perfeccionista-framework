package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementDisplayedMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebListBlockElementDisplayedMarkToStringExtractorCreator implements WebListBlockElementValueExtractorCreator {

    @Override
    public @NotNull WebListBlockValueExtractor<String> createForElement(@NotNull String blockElementName) {
        return new WebListBlockElementDisplayedMarkToStringExtractor(blockElementName);
    }

}

