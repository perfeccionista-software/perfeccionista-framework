package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;

public class WebTextBlockConditionHolder {

    private final ConditionUsage usage;
    private final WebTextListBlockCondition condition;

    private WebTextBlockConditionHolder(ConditionUsage usage, WebTextListBlockCondition condition) {
        this.usage = usage;
        this.condition = condition;
    }

    public static WebTextBlockConditionHolder of(ConditionUsage usage, WebTextListBlockCondition condition) {
        return new WebTextBlockConditionHolder(usage, condition);
    }

    public ConditionUsage getUsage() {
        return usage;
    }

    public WebTextListBlockCondition getCondition() {
        return condition;
    }

}