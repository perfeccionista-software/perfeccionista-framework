package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementPropertyValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebListBlockElementPropertyValueExtractorCreator implements WebListBlockElementValueExtractorCreator {

    private final String propertyName;

    public WebListBlockElementPropertyValueExtractorCreator(@NotNull String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public @NotNull WebListBlockValueExtractor<String> createForElement(@NotNull String blockElementName) {
        return new WebListBlockElementPropertyValueExtractor(blockElementName, propertyName);
    }

}
