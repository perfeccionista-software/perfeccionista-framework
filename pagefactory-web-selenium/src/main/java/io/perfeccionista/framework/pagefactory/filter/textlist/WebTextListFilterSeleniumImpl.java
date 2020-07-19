package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTextListFilterSeleniumImpl implements WebTextListFilter {

    private final Deque<WebTextBlockConditionHolder> conditions = new ArrayDeque<>();

    public WebTextListFilter add(WebTextListBlockCondition condition) {
        this.conditions.addLast(WebTextBlockConditionHolder.of(ConditionUsage.ADD, condition));
        return this;
    }

    public WebTextListFilter subtract(WebTextListBlockCondition condition) {
        this.conditions.addLast(WebTextBlockConditionHolder.of(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public WebTextListFilterResult filter(WebTextList element) {
        return new WebTextListFilterResultSeleniumImpl(element, this);
    }

    public Deque<WebTextBlockConditionHolder> getConditions() {
        return conditions;
    }

}
