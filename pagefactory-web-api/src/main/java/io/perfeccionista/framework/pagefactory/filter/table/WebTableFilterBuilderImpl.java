package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTableFilterBuilderImpl implements WebTableFilterBuilder {

    private final Deque<WebTableRowFilterResultGroupingHolder> conditions = new ArrayDeque<>();

    private WebTableFilterBuilderImpl() {
    }

    public static WebTableFilterBuilderImpl webTableFilterBuilder() {
        return new WebTableFilterBuilderImpl();
    }

    public WebTableFilterBuilder add(@NotNull WebTableRowCondition condition) {
        this.conditions.addLast(WebTableRowFilterResultGroupingHolder.of(FilterResultGrouping.ADD, condition));
        return this;
    }

    public WebTableFilterBuilder subtract(@NotNull WebTableRowCondition condition) {
        this.conditions.addLast(WebTableRowFilterResultGroupingHolder.of(FilterResultGrouping.SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull WebTableFilter build(@NotNull WebTable element) {
        return WebTableFilterImpl.of(element, this);
    }

    public Deque<WebTableRowFilterResultGroupingHolder> getConditions() {
        return conditions;
    }

}
