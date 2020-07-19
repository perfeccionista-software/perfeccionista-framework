package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTextTableFilterSeleniumImpl implements WebTextTableFilter {

    private final Deque<WebTextTableRowConditionHolder> conditions = new ArrayDeque<>();

    public WebTextTableFilter add(WebTextTableRowCondition condition) {
        this.conditions.addLast(new WebTextTableRowConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebTextTableFilter subtract(WebTextTableRowCondition condition) {
        this.conditions.addLast(new WebTextTableRowConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public WebTextTableFilterResult filter(WebTextTable element) {
        return new WebTextTableFilterResultSeleniumImpl(element, this);
    }

    public Deque<WebTextTableRowConditionHolder> getConditions() {
        return conditions;
    }

}
