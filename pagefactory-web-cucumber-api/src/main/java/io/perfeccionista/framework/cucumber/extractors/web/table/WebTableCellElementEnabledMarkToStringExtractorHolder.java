package io.perfeccionista.framework.cucumber.extractors.web.table;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;

public class WebTableCellElementEnabledMarkToStringExtractorHolder implements WebTableCellElementValueExtractorHolder {

    @Override
    public WebTableValueExtractor<String> getForElement(String columnName, String blockElementName) {
        return new WebTableCellElementEnabledMarkToStringExtractor(columnName, blockElementName);
    }

}

