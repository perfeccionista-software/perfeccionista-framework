package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

public class WebListBlockConditionHolder {

    private final ConditionUsage usage;
    private final WebListBlockCondition condition;

    private WebListBlockConditionHolder(ConditionUsage usage, WebListBlockCondition condition) {
        this.usage = usage;
        this.condition = condition;
    }

    public static WebListBlockConditionHolder of(ConditionUsage usage, WebListBlockCondition condition) {
        return new WebListBlockConditionHolder(usage, condition);
    }

    public ConditionUsage getUsage() {
        return usage;
    }

    public WebListBlockCondition getCondition() {
        return condition;
    }

}
