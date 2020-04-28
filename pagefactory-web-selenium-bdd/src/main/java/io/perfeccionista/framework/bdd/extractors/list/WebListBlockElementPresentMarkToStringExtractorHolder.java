package io.perfeccionista.framework.bdd.extractors.list;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;

public class WebListBlockElementPresentMarkToStringExtractorHolder implements WebListBlockValueExtractorHolder {

    @Override
    public WebListBlockValueExtractor<String> getForElement(String blockElementName) {
        return new WebListBlockElementPresentMarkToStringExtractor(blockElementName);
    }

}
