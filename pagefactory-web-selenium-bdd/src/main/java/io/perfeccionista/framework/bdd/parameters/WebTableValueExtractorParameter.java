package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;

public interface WebTableValueExtractorParameter extends BddStepParameter {

    WebTableCellValueExtractor<String> createExtractorFor(String webTableColumnName, String webCellElementName);

}
