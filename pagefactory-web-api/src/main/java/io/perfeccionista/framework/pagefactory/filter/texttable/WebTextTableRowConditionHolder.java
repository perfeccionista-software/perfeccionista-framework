package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

public class WebTextTableRowConditionHolder {

    private final ConditionUsage usage;
    private final WebTextTableRowCondition condition;

    public WebTextTableRowConditionHolder(ConditionUsage usage, WebTextTableRowCondition condition) {
        this.usage = usage;
        this.condition = condition;
    }

    public static WebTextTableRowConditionHolder of(ConditionUsage usage, WebTextTableRowCondition condition) {
        return new WebTextTableRowConditionHolder(usage, condition);
    }

    public ConditionUsage getUsage() {
        return usage;
    }

    public WebTextTableRowCondition getCondition() {
        return condition;
    }

}
