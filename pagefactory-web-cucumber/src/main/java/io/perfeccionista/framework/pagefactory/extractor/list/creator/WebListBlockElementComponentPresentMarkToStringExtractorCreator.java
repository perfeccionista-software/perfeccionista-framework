package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.extractor.list.WebItemElementComponentPresentMarkToStringExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebListBlockElementComponentPresentMarkToStringExtractorCreator
//        implements WebListBlockElementValueExtractorCreator
{

    private final String componentName;

    public WebListBlockElementComponentPresentMarkToStringExtractorCreator(@NotNull String componentName) {
        this.componentName = componentName;
    }

//    @Override
//    public @NotNull WebItemValueExtractor<String, WebBlock> createForElement(@NotNull String blockElementName) {
//        return new WebItemElementComponentPresentMarkToStringExtractor(blockElementName, componentName);
//    }

}
