package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementPresentMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebListBlockElementPresentMarkToStringExtractorCreator implements WebListBlockElementValueExtractorCreator {

    @Override
    public @NotNull WebListBlockValueExtractor<String> createForElement(@NotNull String blockElementName) {
        return new WebListBlockElementPresentMarkToStringExtractor(blockElementName);
    }

}
