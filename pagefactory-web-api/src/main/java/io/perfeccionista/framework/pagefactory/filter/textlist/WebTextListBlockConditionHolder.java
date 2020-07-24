package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

public class WebTextListBlockConditionHolder {

    private final ConditionUsage usage;
    private final WebTextListBlockCondition condition;

    private WebTextListBlockConditionHolder(ConditionUsage usage, WebTextListBlockCondition condition) {
        this.usage = usage;
        this.condition = condition;
    }

    public static WebTextListBlockConditionHolder of(ConditionUsage usage, WebTextListBlockCondition condition) {
        return new WebTextListBlockConditionHolder(usage, condition);
    }

    public ConditionUsage getUsage() {
        return usage;
    }

    public WebTextListBlockCondition getCondition() {
        return condition;
    }

}