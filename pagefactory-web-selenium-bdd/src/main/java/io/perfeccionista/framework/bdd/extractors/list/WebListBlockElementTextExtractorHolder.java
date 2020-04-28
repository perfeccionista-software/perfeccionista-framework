package io.perfeccionista.framework.bdd.extractors.list;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockElementTextValueExtractor;
import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;

public class WebListBlockElementTextExtractorHolder implements WebListBlockValueExtractorHolder {

    @Override
    public WebListBlockValueExtractor<String> getForElement(String blockElementName) {
        return new WebListBlockElementTextValueExtractor(blockElementName);
    }

}
