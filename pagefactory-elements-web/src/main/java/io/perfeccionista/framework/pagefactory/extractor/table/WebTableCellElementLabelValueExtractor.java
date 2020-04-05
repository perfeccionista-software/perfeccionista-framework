package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebTableCellElementLabelValueExtractor implements WebTableCellValueExtractor<String> {

    private final String columnName;
    private final GetLabelAvailable elementMock;
    private final String elementName;

    public WebTableCellElementLabelValueExtractor(String columnName, GetLabelAvailable elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementLabelValueExtractor(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebTableCellElementLabelValueExtractor setHash(String hash) {
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
