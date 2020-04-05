package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebTableCellElementTextValueExtractor implements WebTableCellValueExtractor<String> {

    private final String columnName;
    private final GetTextAvailable elementMock;
    private final String elementName;

    public WebTableCellElementTextValueExtractor(String columnName, GetTextAvailable elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementTextValueExtractor(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebTableCellElementTextValueExtractor setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<String> extractSingleHeaderValue(WebTable element) {
        return null;
    }

    @Override
    public SingleResult<String> extractSingleValue(WebTable element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<String> extractMultipleValues(WebTable element, MultipleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public SingleResult<String> extractSingleFooterValue(WebTable element) {
        return null;
    }

}
