package io.perfeccionista.framework.pagefactory.filter.list.condition;

import io.perfeccionista.framework.pagefactory.elements.MobileList;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface MobileListBlockCondition {

    MobileListBlockCondition and(@NotNull MobileListBlockCondition condition);

    MobileListBlockCondition or(@NotNull MobileListBlockCondition condition);

    Deque<MobileListBlockConditionHolder> getChildConditions();

    @NotNull FilterResult process(@NotNull MobileList element, @Nullable String hash);

    class MobileListBlockConditionHolder {

        private final ConditionGrouping usage;
        private final MobileListBlockCondition condition;

        private MobileListBlockConditionHolder(ConditionGrouping usage, MobileListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static MobileListBlockConditionHolder of(ConditionGrouping usage, MobileListBlockCondition condition) {
            return new MobileListBlockConditionHolder(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public MobileListBlockCondition getCondition() {
            return condition;
        }

    }

}
