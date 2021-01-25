package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.MobileTextTableRowCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class MobileTextTableFilterBuilderImpl implements MobileTextTableFilterBuilder {

    private final Deque<MobileTextTableRowFilterResultGroupingHolder> conditions = new ArrayDeque<>();

    private MobileTextTableFilterBuilderImpl() {
    }

    public static MobileTextTableFilterBuilderImpl mobileTextTableFilterBuilder() {
        return new MobileTextTableFilterBuilderImpl();
    }

    public MobileTextTableFilterBuilder add(@NotNull MobileTextTableRowCondition condition) {
        this.conditions.addLast(new MobileTextTableRowFilterResultGroupingHolder(FilterResultGrouping.ADD, condition));
        return this;
    }

    public MobileTextTableFilterBuilder subtract(@NotNull MobileTextTableRowCondition condition) {
        this.conditions.addLast(new MobileTextTableRowFilterResultGroupingHolder(FilterResultGrouping.SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull MobileTextTableFilter build(@NotNull MobileTextTable element) {
        return MobileTextTableFilterImpl.of(element, this);
    }

    public Deque<MobileTextTableRowFilterResultGroupingHolder> getConditions() {
        return conditions;
    }

}
