package io.perfeccionista.framework.pagefactory.extractor.list.creator;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.extractor.WebItemValueExtractor;
import org.jetbrains.annotations.NotNull;

public interface WebListBlockElementValueExtractorCreator {

    @NotNull WebItemValueExtractor<String, WebBlock<?>> createForElement(@NotNull String blockElementName);

}
