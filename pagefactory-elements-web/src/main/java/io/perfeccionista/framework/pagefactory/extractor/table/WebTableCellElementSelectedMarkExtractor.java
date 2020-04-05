package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.extractor.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebTableCellElementSelectedMarkExtractor implements WebTableCellValueExtractor<Boolean> {

    private final String columnName;
    private final IsSelectedAvailable elementMock;
    private final String elementName;

    public WebTableCellElementSelectedMarkExtractor(String columnName, IsSelectedAvailable elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementSelectedMarkExtractor(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebTableCellElementSelectedMarkExtractor setHash(String hash) {
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

