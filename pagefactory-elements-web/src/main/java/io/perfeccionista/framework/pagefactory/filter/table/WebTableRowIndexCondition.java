package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.value.number.NumberValue;

public class WebTableRowIndexCondition implements WebTableCellCondition {

    private final NumberValue<Integer> value;

    public WebTableRowIndexCondition(NumberValue<Integer> value) {
        this.value = value;
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
