package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;

public interface WebListValueExtractorParameter extends BddStepParameter {

    WebListBlockValueExtractor<String> createExtractorFor(String blockElementName);

}
