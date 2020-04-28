package io.perfeccionista.framework.bdd.extractors.table;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;

public interface WebTableCellElementValueExtractorHolder {

    WebTableCellValueExtractor<String> getForElement(String columnName, String blockElementName);

}
