package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementAttributeValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebListBlockElementPropertyValueExtractorCreator
//        implements WebListBlockElementValueExtractorCreator
{

    private final String propertyName;

    public WebListBlockElementPropertyValueExtractorCreator(@NotNull String propertyName) {
        this.propertyName = propertyName;
    }

//    @Override
//    public @NotNull WebItemValueExtractor<String, WebBlock> createForElement(@NotNull String blockElementName) {
//        return new WebItemElementAttributeValueExtractor<>(blockElementName, propertyName);
//    }

}
