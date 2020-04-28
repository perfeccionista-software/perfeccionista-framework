package io.perfeccionista.framework.bdd.extractors.list;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;

public class WebListBlockElementDisplayedMarkToStringExtractorHolder implements WebListBlockValueExtractorHolder {

    @Override
    public WebListBlockValueExtractor<String> getForElement(String blockElementName) {
        return new WebListBlockElementDisplayedMarkToStringExtractor(blockElementName);
    }

}
