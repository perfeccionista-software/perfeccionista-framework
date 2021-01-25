package io.perfeccionista.framework.pagefactory.filter.table.condition;

import io.perfeccionista.framework.pagefactory.elements.MobileTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface MobileTableRowCondition {

    MobileTableRowCondition and(@NotNull MobileTableRowCondition condition);

    MobileTableRowCondition or(@NotNull MobileTableRowCondition condition);

    Deque<MobileTableRowConditionHolder> getChildConditions();

    @NotNull FilterResult process(@NotNull MobileTable element, @Nullable String hash);

    class MobileTableRowConditionHolder {

        private final ConditionGrouping usage;
        private final MobileTableRowCondition condition;

        private MobileTableRowConditionHolder(ConditionGrouping usage, MobileTableRowCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static MobileTableRowConditionHolder of(ConditionGrouping usage, MobileTableRowCondition condition) {
            return new MobileTableRowConditionHolder(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public MobileTableRowCondition getCondition() {
            return condition;
        }

    }

}
