package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebTableCellElementExtractor<T extends WebChildElement> implements WebTableCellValueExtractor<T> {

    private final String columnName;
    private final T elementMock;
    private final String elementName;
    private final Class<T> returnType;

    public WebTableCellElementExtractor(String columnName, T elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
        this.returnType = null;
    }

    public WebTableCellElementExtractor(String columnName, String elementName, Class<T> returnType) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
        this.returnType = returnType;
    }

    @Override
    public WebTableCellElementExtractor<T> setHash(String hash) {
        return null;
    }

    @Override
    public SingleResult<T> extractSingleHeaderValue(WebTable element) {
        return null;
    }

    @Override
    public SingleResult<T> extractSingleValue(WebTable element, SingleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public MultipleResult<T> extractMultipleValues(WebTable element, MultipleResult<Integer> filterResult) {
        return null;
    }

    @Override
    public SingleResult<T> extractSingleFooterValue(WebTable element) {
        return null;
    }

}
