package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.MobileFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.condition.MobileTableRowCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface MobileTableFilterBuilder extends MobileFilterBuilder<MobileTable, MobileTableFilter> {

    @Override
    @NotNull MobileTableFilter build(@NotNull MobileTable element);

    MobileTableFilterBuilder add(@NotNull MobileTableRowCondition condition);

    MobileTableFilterBuilder subtract(@NotNull MobileTableRowCondition condition);

    Deque<MobileTableRowFilterResultGroupingHolder> getConditions();

    class MobileTableRowFilterResultGroupingHolder {

        private final FilterResultGrouping usage;
        private final MobileTableRowCondition condition;

        private MobileTableRowFilterResultGroupingHolder(FilterResultGrouping usage, MobileTableRowCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static MobileTableRowFilterResultGroupingHolder of(FilterResultGrouping usage, MobileTableRowCondition condition) {
            return new MobileTableRowFilterResultGroupingHolder(usage, condition);
        }

        public FilterResultGrouping getUsage() {
            return usage;
        }

        public MobileTableRowCondition getCondition() {
            return condition;
        }

    }

}
