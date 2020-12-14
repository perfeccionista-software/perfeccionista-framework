package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.pagefactory.extractor.list.WebListBlockValueExtractor;

public interface WebListValueExtractorParameter extends CucumberStepParameter {

    WebListBlockValueExtractor<String> createExtractorFor(String blockElementName);

}
