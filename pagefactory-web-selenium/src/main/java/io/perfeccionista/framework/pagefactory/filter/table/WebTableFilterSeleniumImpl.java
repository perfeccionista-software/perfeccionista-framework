package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTableFilterSeleniumImpl implements WebTableFilter {

    private final Deque<JsTableRowConditionHolder> conditions = new ArrayDeque<>();

    public WebTableFilter add(WebTableCellCondition condition) {
        this.conditions.addLast(new JsTableRowConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebTableFilter subtract(WebTableCellCondition condition) {
        this.conditions.addLast(new JsTableRowConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public WebTableFilterResult filter(WebTable element) {
        return new WebTableFilterResultSeleniumImpl(element, this);
    }

    public Deque<JsTableRowConditionHolder> getConditions() {
        return conditions;
    }

}
