package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetTextAvailable;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebTableCellElementTextCondition implements WebTableCellCondition {

    private final String columnName;
    private final String elementName;
    private final GetTextAvailable elementMock;

    private final StringValue stringValue;
    private final NumberValue<? extends Number> numberValue;

    public WebTableCellElementTextCondition(String columnName, GetTextAvailable elementMock, StringValue stringValue) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebTableCellElementTextCondition(String columnName, String elementName, StringValue stringValue) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebTableCellElementTextCondition(String columnName, GetTextAvailable elementMock, NumberValue<? extends Number> numberValue) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebTableCellElementTextCondition(String columnName, String elementName, NumberValue<? extends Number> numberValue) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    @Override
    public WebTableCellCondition and(WebTableCellCondition condition) {
        return null;
    }

    @Override
    public WebTableCellCondition or(WebTableCellCondition condition) {
        return null;
    }

}
