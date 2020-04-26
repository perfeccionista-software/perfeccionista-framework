package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.extractor.table.WebTableCellValueExtractor;

public class WebTableValueExtractorParameterImpl implements WebTableValueExtractorParameter {

    private final Environment environment;
    private final String rawInput;

    public WebTableValueExtractorParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public WebTableCellValueExtractor<String> findForElement(String webTableColumnName, String webCellElementName) {
        return null;
    }

    @Override
    public String getRaw() {
        return null;
    }

}
