package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebListFilterSeleniumImpl implements WebListFilter {

    private final Deque<WebListBlockConditionHolder> conditions = new ArrayDeque<>();

    public WebListFilter add(WebListBlockCondition condition) {
        this.conditions.addLast(new WebListBlockConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebListFilter subtract(WebListBlockCondition condition) {
        this.conditions.addLast(new WebListBlockConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public WebListFilterResult filter(WebList element) {
        return new WebListFilterResultSeleniumImpl(element, this);
    }

    public Deque<WebListBlockConditionHolder> getConditions() {
        return conditions;
    }

}
