package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebTableCellElementPropertyCondition implements WebTableCellCondition {

    private final String columnName;
    private final WebChildElement elementMock;
    private final String elementName;

    private final String propertyName;
    private final StringValue stringValue;
    private final NumberValue<? extends Number> numberValue;

    public WebTableCellElementPropertyCondition(String columnName, WebChildElement elementMock, String propertyName, StringValue stringValue) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
        this.propertyName = propertyName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebTableCellElementPropertyCondition(String columnName, WebChildElement elementMock, String propertyName, NumberValue<? extends Number> numberValue) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
        this.propertyName = propertyName;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebTableCellElementPropertyCondition(String columnName, String elementName, String propertyName, StringValue stringValue) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
        this.propertyName = propertyName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebTableCellElementPropertyCondition(String columnName, String elementName, String propertyName, NumberValue<? extends Number> numberValue) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
        this.propertyName = propertyName;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebTableCellElementPropertyCondition inverse() {

        return this;
    }

    @Override
    public WebTableCellCondition and(WebTableCellCondition condition) {

        return this;
    }

    @Override
    public WebTableCellCondition or(WebTableCellCondition condition) {

        return this;
    }

}