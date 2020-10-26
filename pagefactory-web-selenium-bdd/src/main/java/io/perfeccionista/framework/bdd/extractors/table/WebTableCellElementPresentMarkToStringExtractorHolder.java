package io.perfeccionista.framework.bdd.extractors.table;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;

public class WebTableCellElementPresentMarkToStringExtractorHolder implements WebTableCellElementValueExtractorHolder {

    @Override
    public WebTableValueExtractor<String> getForElement(String columnName, String blockElementName) {
        return new WebTableCellElementPresentMarkToStringExtractor(columnName, blockElementName);
    }

}
