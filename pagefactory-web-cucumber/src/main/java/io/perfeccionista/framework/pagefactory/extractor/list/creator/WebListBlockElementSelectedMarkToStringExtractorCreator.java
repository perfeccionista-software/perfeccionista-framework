package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementSelectedMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebListBlockElementSelectedMarkToStringExtractorCreator implements WebListBlockElementValueExtractorCreator {

    @Override
    public @NotNull WebListBlockValueExtractor<String> createForElement(@NotNull String blockElementName) {
        return new WebListBlockElementSelectedMarkToStringExtractor(blockElementName);
    }

}
