package io.perfeccionista.framework.pagefactory.filter.stringtable;

import io.perfeccionista.framework.value.number.NumberValue;

public class WebStringTableRowIndexCondition implements WebStringTableCellCondition {

    private final NumberValue<Integer> value;

    public WebStringTableRowIndexCondition(NumberValue<Integer> value) {
        this.value = value;
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
