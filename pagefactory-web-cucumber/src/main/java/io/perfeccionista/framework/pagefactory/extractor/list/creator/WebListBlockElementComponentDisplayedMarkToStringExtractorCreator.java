package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementComponentDisplayedMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebListBlockElementComponentDisplayedMarkToStringExtractorCreator implements WebListBlockElementValueExtractorCreator {

    private final String componentName;

    public WebListBlockElementComponentDisplayedMarkToStringExtractorCreator(@NotNull String componentName) {
        this.componentName = componentName;
    }

    @Override
    public @NotNull WebListBlockValueExtractor<String> createForElement(@NotNull String blockElementName) {
        return new WebListBlockElementComponentDisplayedMarkToStringExtractor(blockElementName, componentName);
    }

}
