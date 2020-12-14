package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;

public interface WebTableValueExtractorParameter extends CucumberStepParameter {

    WebTableValueExtractor<String> createExtractorFor(String webTableColumnName, String webCellElementName);

}
