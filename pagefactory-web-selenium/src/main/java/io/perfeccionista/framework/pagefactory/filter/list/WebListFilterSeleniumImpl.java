package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

import java.util.ArrayDeque;
import java.util.Deque;

import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.*;

public class WebListFilterSeleniumImpl implements WebListFilter {

    private final Deque<WebListBlockConditionHolder> conditions = new ArrayDeque<>();

    public WebListFilter add(WebListBlockCondition condition) {
        this.conditions.addLast(WebListBlockConditionHolder.of(ADD, condition));
        return this;
    }

    public WebListFilter subtract(WebListBlockCondition condition) {
        this.conditions.addLast(WebListBlockConditionHolder.of(SUBTRACT, condition));
        return this;
    }

    @Override
    public WebListFilterResult filter(WebList element) {
        return WebListFilterResultSeleniumImpl.of(element, this);
    }

    public Deque<WebListBlockConditionHolder> getConditions() {
        return conditions;
    }

}
