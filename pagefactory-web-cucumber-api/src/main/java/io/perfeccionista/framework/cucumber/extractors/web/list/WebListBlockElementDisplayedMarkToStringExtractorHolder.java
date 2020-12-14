package io.perfeccionista.framework.cucumber.extractors.web.list;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;

public class WebListBlockElementDisplayedMarkToStringExtractorHolder implements WebListBlockValueExtractorHolder {

    @Override
    public WebListBlockValueExtractor<String> getForElement(String blockElementName) {
        return new WebListBlockElementDisplayedMarkToStringExtractor(blockElementName);
    }

}
