package io.perfeccionista.framework.cucumber.extractors.web.list;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;

public interface WebListBlockValueExtractorHolder {

    WebListBlockValueExtractor<String> getForElement(String blockElementName);

}
