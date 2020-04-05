package io.perfeccionista.framework.pagefactory.extractor.table;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.extractor.WebTableCellValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;

public class WebTableCellElementPropertyValueExtractor implements WebTableCellValueExtractor<String> {

    private final String columnName;
    private final String propertyName;
    private final WebChildElement elementMock;
    private final String elementName;

    public WebTableCellElementPropertyValueExtractor(String columnName, WebChildElement elementMock, String propertyName) {
        this.columnName = columnName;
        this.propertyName = propertyName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementPropertyValueExtractor(String columnName, String elementName, String propertyName) {
        this.columnName = columnName;
        this.propertyName = propertyName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebTableCellElementPropertyValueExtractor setHash(String hash) {
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

