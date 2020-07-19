package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

public class WebRadioButtonConditionHolder {

    private final ConditionUsage usage;
    private final WebRadioButtonCondition condition;

    public WebRadioButtonConditionHolder(ConditionUsage usage, WebRadioButtonCondition condition) {
        this.usage = usage;
        this.condition = condition;
    }

    public static WebRadioButtonConditionHolder of(ConditionUsage usage, WebRadioButtonCondition condition) {
        return new WebRadioButtonConditionHolder(usage, condition);
    }

    public ConditionUsage getUsage() {
        return usage;
    }

    public WebRadioButtonCondition getCondition() {
        return condition;
    }

}
