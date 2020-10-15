package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTextTableFilterBuilderImpl implements WebTextTableFilterBuilder {

    private final Deque<WebTextTableRowFilterResultGroupingHolder> conditions = new ArrayDeque<>();

    private WebTextTableFilterBuilderImpl() {
    }

    public static WebTextTableFilterBuilderImpl webTextTableFilterBuilder() {
        return new WebTextTableFilterBuilderImpl();
    }

    public WebTextTableFilterBuilder add(@NotNull WebTextTableRowCondition condition) {
        this.conditions.addLast(new WebTextTableRowFilterResultGroupingHolder(WebFilterResultGrouping.ADD, condition));
        return this;
    }

    public WebTextTableFilterBuilder subtract(@NotNull WebTextTableRowCondition condition) {
        this.conditions.addLast(new WebTextTableRowFilterResultGroupingHolder(WebFilterResultGrouping.SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull WebTextTableFilter build(@NotNull WebTextTable element) {
        return WebTextTableFilterImpl.of(element, this);
    }

    public Deque<WebTextTableRowFilterResultGroupingHolder> getConditions() {
        return conditions;
    }

}
