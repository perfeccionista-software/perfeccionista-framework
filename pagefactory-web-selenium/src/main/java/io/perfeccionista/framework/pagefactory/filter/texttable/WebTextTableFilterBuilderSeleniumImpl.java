package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTextTableFilterBuilderSeleniumImpl implements WebTextTableFilterBuilder {

    private final Deque<WebTextTableRowConditionHolder> conditions = new ArrayDeque<>();

    public WebTextTableFilterBuilder add(WebTextTableRowCondition condition) {
        this.conditions.addLast(new WebTextTableRowConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebTextTableFilterBuilder subtract(WebTextTableRowCondition condition) {
        this.conditions.addLast(new WebTextTableRowConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public WebTextTableFilter build(WebTextTable element) {
        return new WebTextTableFilterSeleniumImpl(element, this);
    }

    public Deque<WebTextTableRowConditionHolder> getConditions() {
        return conditions;
    }

}
