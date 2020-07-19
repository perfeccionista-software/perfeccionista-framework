package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTextListFilterBuilderSeleniumImpl implements WebTextListFilterBuilder {

    private final Deque<WebTextBlockConditionHolder> conditions = new ArrayDeque<>();

    public WebTextListFilterBuilder add(WebTextListBlockCondition condition) {
        this.conditions.addLast(WebTextBlockConditionHolder.of(ConditionUsage.ADD, condition));
        return this;
    }

    public WebTextListFilterBuilder subtract(WebTextListBlockCondition condition) {
        this.conditions.addLast(WebTextBlockConditionHolder.of(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public WebTextListFilter build(WebTextList element) {
        return new WebTextListFilterSeleniumImpl(element, this);
    }

    public Deque<WebTextBlockConditionHolder> getConditions() {
        return conditions;
    }

}
