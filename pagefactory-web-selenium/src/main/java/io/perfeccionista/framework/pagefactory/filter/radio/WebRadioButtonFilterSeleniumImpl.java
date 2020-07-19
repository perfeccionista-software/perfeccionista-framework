package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebRadioButtonFilterSeleniumImpl implements WebRadioButtonFilter {

    private final Deque<WebRadioButtonConditionHolder> conditions = new ArrayDeque<>();

    public WebRadioButtonFilter add(WebRadioButtonCondition condition) {
        this.conditions.addLast(new WebRadioButtonConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebRadioButtonFilter subtract(WebRadioButtonCondition condition) {
        this.conditions.addLast(new WebRadioButtonConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public WebRadioButtonFilterResult filter(WebRadioGroup element) {
        return new WebRadioButtonFilterResultSeleniumImpl(element, this);
    }

    public Deque<WebRadioButtonConditionHolder> getConditions() {
        return conditions;
    }

}
