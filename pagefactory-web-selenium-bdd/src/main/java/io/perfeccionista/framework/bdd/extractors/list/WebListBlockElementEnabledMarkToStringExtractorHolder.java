package io.perfeccionista.framework.bdd.extractors.list;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;

public class WebListBlockElementEnabledMarkToStringExtractorHolder implements WebListBlockValueExtractorHolder {

    @Override
    public WebListBlockValueExtractor<String> getForElement(String blockElementName) {
        return new WebListBlockElementEnabledMarkToStringExtractor(blockElementName);
    }

}
