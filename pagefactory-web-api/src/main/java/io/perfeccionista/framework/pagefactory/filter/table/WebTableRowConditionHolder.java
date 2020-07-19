package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

public class WebTableRowConditionHolder {

    private final ConditionUsage usage;
    private final WebTableRowCondition condition;

    private WebTableRowConditionHolder(ConditionUsage usage, WebTableRowCondition condition) {
        this.usage = usage;
        this.condition = condition;
    }

    public static WebTableRowConditionHolder of(ConditionUsage usage, WebTableRowCondition condition) {
        return new WebTableRowConditionHolder(usage, condition);
    }

    public ConditionUsage getUsage() {
        return usage;
    }

    public WebTableRowCondition getCondition() {
        return condition;
    }

}
