package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTextTableFilterSeleniumImpl implements WebTextTableFilter {

    private final Deque<JsStringTableRowConditionHolder> conditions = new ArrayDeque<>();

    public WebTextTableFilter add(WebTextTableCellCondition condition) {
        this.conditions.addLast(new JsStringTableRowConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebTextTableFilter subtract(WebTextTableCellCondition condition) {
        this.conditions.addLast(new JsStringTableRowConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public WebTextTableFilterResult filter(WebTextTable element) {
        return new WebTextTableFilterResultSeleniumImpl(element, this);
    }

    public Deque<JsStringTableRowConditionHolder> getConditions() {
        return conditions;
    }

}
