package io.perfeccionista.framework.cucumber.extractors.web.table;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;

public interface WebTableCellElementValueExtractorHolder {

    WebTableValueExtractor<String> getForElement(String columnName, String blockElementName);

}
