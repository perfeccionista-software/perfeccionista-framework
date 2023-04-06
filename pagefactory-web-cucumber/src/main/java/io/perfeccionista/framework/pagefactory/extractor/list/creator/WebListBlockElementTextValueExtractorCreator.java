package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.extractor.WebItemElementTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import org.jetbrains.annotations.NotNull;

public class WebListBlockElementTextValueExtractorCreator implements WebListBlockElementValueExtractorCreator {

    @Override
    public @NotNull WebItemValueExtractor<String, WebBlock<?>> createForElement(@NotNull String blockElementName) {
        return new WebItemElementTextValueExtractor<>(blockElementName);
    }

}
