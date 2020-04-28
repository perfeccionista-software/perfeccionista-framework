package io.perfeccionista.framework.bdd.extractors.list;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;

public interface WebListBlockValueExtractorHolder {

    WebListBlockValueExtractor<String> getForElement(String blockElementName);

}
