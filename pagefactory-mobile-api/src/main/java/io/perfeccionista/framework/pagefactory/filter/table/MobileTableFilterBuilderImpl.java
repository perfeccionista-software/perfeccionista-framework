package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.table.condition.MobileTableRowCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class MobileTableFilterBuilderImpl implements MobileTableFilterBuilder {

    private final Deque<MobileTableRowFilterResultGroupingHolder> conditions = new ArrayDeque<>();

    private MobileTableFilterBuilderImpl() {
    }

    public static MobileTableFilterBuilderImpl mobileTableFilterBuilder() {
        return new MobileTableFilterBuilderImpl();
    }

    public MobileTableFilterBuilder add(@NotNull MobileTableRowCondition condition) {
        this.conditions.addLast(MobileTableRowFilterResultGroupingHolder.of(FilterResultGrouping.ADD, condition));
        return this;
    }

    public MobileTableFilterBuilder subtract(@NotNull MobileTableRowCondition condition) {
        this.conditions.addLast(MobileTableRowFilterResultGroupingHolder.of(FilterResultGrouping.SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull MobileTableFilter build(@NotNull MobileTable element) {
        return MobileTableFilterImpl.of(element, this);
    }

    public Deque<MobileTableRowFilterResultGroupingHolder> getConditions() {
        return conditions;
    }

}
