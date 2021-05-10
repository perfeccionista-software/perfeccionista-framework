package io.perfeccionista.framework.pagefactory.filter.texttable.condition;

import io.perfeccionista.framework.pagefactory.elements.MobileTextTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface MobileTextTableRowCondition {

    MobileTextTableRowCondition and(@NotNull MobileTextTableRowCondition condition);

    MobileTextTableRowCondition or(@NotNull MobileTextTableRowCondition condition);

    Deque<MobileTextTableRowConditionHolder> getChildConditions();

    @NotNull FilterResult process(@NotNull MobileTextTable element, @Nullable String hash);

    class MobileTextTableRowConditionHolder {

        private final ConditionGrouping usage;
        private final MobileTextTableRowCondition condition;

        public MobileTextTableRowConditionHolder(ConditionGrouping usage, MobileTextTableRowCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static MobileTextTableRowConditionHolder of(ConditionGrouping usage, MobileTextTableRowCondition condition) {
            return new MobileTextTableRowConditionHolder(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public MobileTextTableRowCondition getCondition() {
            return condition;
        }

    }

}
