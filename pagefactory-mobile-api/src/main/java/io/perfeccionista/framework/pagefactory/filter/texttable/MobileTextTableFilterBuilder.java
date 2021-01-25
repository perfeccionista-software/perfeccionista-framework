package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.MobileFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.MobileTextTableRowCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface MobileTextTableFilterBuilder extends MobileFilterBuilder<MobileTextTable, MobileTextTableFilter> {

    @Override
    @NotNull MobileTextTableFilter build(@NotNull MobileTextTable element);

    MobileTextTableFilterBuilder add(@NotNull MobileTextTableRowCondition condition);

    MobileTextTableFilterBuilder subtract(@NotNull MobileTextTableRowCondition condition);

    Deque<MobileTextTableRowFilterResultGroupingHolder> getConditions();

    class MobileTextTableRowFilterResultGroupingHolder {

        private final FilterResultGrouping usage;
        private final MobileTextTableRowCondition condition;

        public MobileTextTableRowFilterResultGroupingHolder(FilterResultGrouping usage, MobileTextTableRowCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static MobileTextTableRowFilterResultGroupingHolder of(FilterResultGrouping usage, MobileTextTableRowCondition condition) {
            return new MobileTextTableRowFilterResultGroupingHolder(usage, condition);
        }

        public FilterResultGrouping getUsage() {
            return usage;
        }

        public MobileTextTableRowCondition getCondition() {
            return condition;
        }

    }

}
