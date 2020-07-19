package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebRadioButtonFilterBuilderSeleniumImpl implements WebRadioButtonFilterBuilder {

    private final Deque<WebRadioButtonConditionHolder> conditions = new ArrayDeque<>();

    public WebRadioButtonFilterBuilder add(WebRadioButtonCondition condition) {
        this.conditions.addLast(new WebRadioButtonConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebRadioButtonFilterBuilder subtract(WebRadioButtonCondition condition) {
        this.conditions.addLast(new WebRadioButtonConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public WebRadioButtonFilter build(WebRadioGroup element) {
        return new WebRadioButtonFilterSeleniumImpl(element, this);
    }

    public Deque<WebRadioButtonConditionHolder> getConditions() {
        return conditions;
    }

}
