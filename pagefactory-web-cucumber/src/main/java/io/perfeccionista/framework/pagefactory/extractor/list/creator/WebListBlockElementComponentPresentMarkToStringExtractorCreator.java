package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementComponentPresentMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebListBlockElementComponentPresentMarkToStringExtractorCreator implements WebListBlockElementValueExtractorCreator {

    private final String componentName;

    public WebListBlockElementComponentPresentMarkToStringExtractorCreator(@NotNull String componentName) {
        this.componentName = componentName;
    }

    @Override
    public @NotNull WebListBlockValueExtractor<String> createForElement(@NotNull String blockElementName) {
        return new WebListBlockElementComponentPresentMarkToStringExtractor(blockElementName, componentName);
    }

}
