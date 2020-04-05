package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebTableCellElementDisplayedMarkExtractor implements WebTableCellValueExtractor<Boolean> {

    private final String columnName;
    private final IsDisplayedAvailable elementMock;
    private final String elementName;

    public WebTableCellElementDisplayedMarkExtractor(String columnName, IsDisplayedAvailable elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementDisplayedMarkExtractor(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebTableCellElementDisplayedMarkExtractor setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<Boolean> extractSingleHeaderValue(WebTable element) {
        return null;
    }

    @Override
    public SingleResult<Boolean> extractSingleValue(WebTable element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<Boolean> extractMultipleValues(WebTable element, MultipleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public SingleResult<Boolean> extractSingleFooterValue(WebTable element) {
        return null;
    }

}
