package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTableFilterBuilderSeleniumImpl implements WebTableFilterBuilder {

    private final Deque<WebTableRowConditionHolder> conditions = new ArrayDeque<>();

    public WebTableFilterBuilder add(@NotNull WebTableRowCondition condition) {
        this.conditions.addLast(WebTableRowConditionHolder.of(ConditionUsage.ADD, condition));
        return this;
    }

    public WebTableFilterBuilder subtract(@NotNull WebTableRowCondition condition) {
        this.conditions.addLast(WebTableRowConditionHolder.of(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull WebTableFilter build(@NotNull WebTable element) {
        return WebTableFilterSeleniumImpl.of(element, this);
    }

    public Deque<WebTableRowConditionHolder> getConditions() {
        return conditions;
    }

}
