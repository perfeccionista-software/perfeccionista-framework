package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTableFilterSeleniumImpl implements WebTableFilter {

    private final Deque<WebTableRowConditionHolder> conditions = new ArrayDeque<>();

    public WebTableFilter add(WebTableRowCondition condition) {
        this.conditions.addLast(WebTableRowConditionHolder.of(ConditionUsage.ADD, condition));
        return this;
    }

    public WebTableFilter subtract(WebTableRowCondition condition) {
        this.conditions.addLast(WebTableRowConditionHolder.of(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public WebTableFilterResult filter(WebTable element) {
        return WebTableFilterResultSeleniumImpl.of(element, this);
    }

    public Deque<WebTableRowConditionHolder> getConditions() {
        return conditions;
    }

}
