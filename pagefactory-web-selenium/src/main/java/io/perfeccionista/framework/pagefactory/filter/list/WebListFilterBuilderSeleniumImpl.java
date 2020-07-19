package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;

import java.util.ArrayDeque;
import java.util.Deque;

import static io.perfeccionista.framework.pagefactory.filter.ConditionUsage.*;

public class WebListFilterBuilderSeleniumImpl implements WebListFilterBuilder {

    private final Deque<WebListBlockConditionHolder> conditions = new ArrayDeque<>();

    public WebListFilterBuilder add(WebListBlockCondition condition) {
        this.conditions.addLast(WebListBlockConditionHolder.of(ADD, condition));
        return this;
    }

    public WebListFilterBuilder subtract(WebListBlockCondition condition) {
        this.conditions.addLast(WebListBlockConditionHolder.of(SUBTRACT, condition));
        return this;
    }

    @Override
    public WebListFilter build(WebList element) {
        return WebListFilterSeleniumImpl.of(element, this);
    }

    public Deque<WebListBlockConditionHolder> getConditions() {
        return conditions;
    }

}
