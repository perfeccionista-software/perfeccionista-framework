package io.perfeccionista.framework.bdd.extractors.table;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;

public class WebTableCellElementEnabledMarkToStringExtractorHolder implements WebTableCellElementValueExtractorHolder {

    @Override
    public WebTableCellValueExtractor<String> getForElement(String columnName, String blockElementName) {
        return new WebTableCellElementEnabledMarkToStringExtractor(columnName, blockElementName);
    }

}

