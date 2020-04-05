package io.perfeccionista.framework.pagefactory.filter.stringtable;

import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebStringTableCellTextCondition implements WebStringTableCellCondition {

    private final String columnName;
    private final StringValue stringValue;
    private final NumberValue<?> numberValue;

    public WebStringTableCellTextCondition(String columnName, StringValue stringValue) {
        this.columnName = columnName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebStringTableCellTextCondition(String columnName, NumberValue<?> numberValue) {
        this.columnName = columnName;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    @Override
    public WebStringTableCellCondition and(WebStringTableCellCondition condition) {
        return null;
    }

    @Override
    public WebStringTableCellCondition or(WebStringTableCellCondition condition) {
        return null;
    }

}
