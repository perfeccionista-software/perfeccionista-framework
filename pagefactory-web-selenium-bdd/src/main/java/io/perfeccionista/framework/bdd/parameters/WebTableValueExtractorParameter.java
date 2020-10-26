package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;

public interface WebTableValueExtractorParameter extends BddStepParameter {

    WebTableValueExtractor<String> createExtractorFor(String webTableColumnName, String webCellElementName);

}
