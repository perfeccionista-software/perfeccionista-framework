package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.extractor.list.WebItemElementComponentDisplayedMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebListBlockElementComponentDisplayedMarkToStringExtractorCreator
//        implements WebListBlockElementValueExtractorCreator
{

    private final String componentName;

    public WebListBlockElementComponentDisplayedMarkToStringExtractorCreator(@NotNull String componentName) {
        this.componentName = componentName;
    }

//    @Override
//    public @NotNull WebItemValueExtractor<String, WebBlock> createForElement(@NotNull String blockElementName) {
//        return new WebItemElementComponentDisplayedMarkToStringExtractor(blockElementName, componentName);
//    }

}
