package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.value.number.NumberValue;

public class WebTextTableRowIndexCondition implements WebTextTableCellCondition {

    private final NumberValue<Integer> value;

    public WebTextTableRowIndexCondition(NumberValue<Integer> value) {
        this.value = value;
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
