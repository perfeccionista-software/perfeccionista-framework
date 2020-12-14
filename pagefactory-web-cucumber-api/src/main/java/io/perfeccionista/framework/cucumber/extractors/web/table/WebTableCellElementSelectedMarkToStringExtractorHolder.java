package io.perfeccionista.framework.cucumber.extractors.web.table;

import io.perfeccionista.framework.pagefactory.extractor.table.WebTableValueExtractor;

public class WebTableCellElementSelectedMarkToStringExtractorHolder implements WebTableCellElementValueExtractorHolder {

    @Override
    public WebTableValueExtractor<String> getForElement(String columnName, String blockElementName) {
        return new WebTableCellElementSelectedMarkToStringExtractor(columnName, blockElementName);
    }

}
