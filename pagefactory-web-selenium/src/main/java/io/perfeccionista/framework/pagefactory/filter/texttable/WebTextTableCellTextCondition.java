package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebTextTableCellTextCondition implements WebTextTableCellCondition {

    private final String columnName;
    private final StringValue stringValue;
    private final NumberValue<?> numberValue;

    public WebTextTableCellTextCondition(String columnName, StringValue stringValue) {
        this.columnName = columnName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebTextTableCellTextCondition(String columnName, NumberValue<?> numberValue) {
        this.columnName = columnName;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebTextTableCellTextCondition inverse() {

        return this;
    }

    @Override
    public WebTextTableCellCondition and(WebTextTableCellCondition condition) {
        return null;
    }

    @Override
    public WebTextTableCellCondition or(WebTextTableCellCondition condition) {
        return null;
    }

}
