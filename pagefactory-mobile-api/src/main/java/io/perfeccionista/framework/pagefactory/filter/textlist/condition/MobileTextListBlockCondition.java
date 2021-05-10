package io.perfeccionista.framework.pagefactory.filter.textlist.condition;

import io.perfeccionista.framework.pagefactory.elements.MobileTextList;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface MobileTextListBlockCondition {

    MobileTextListBlockCondition and(@NotNull MobileTextListBlockCondition condition);

    MobileTextListBlockCondition or(@NotNull MobileTextListBlockCondition condition);

    Deque<MobileTextListBlockConditionHolder> getChildConditions();

    @NotNull FilterResult process(@NotNull MobileTextList element, @Nullable String hash);

    class MobileTextListBlockConditionHolder {

        private final ConditionGrouping usage;
        private final MobileTextListBlockCondition condition;

        private MobileTextListBlockConditionHolder(ConditionGrouping usage, MobileTextListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static MobileTextListBlockConditionHolder of(ConditionGrouping usage, MobileTextListBlockCondition condition) {
            return new MobileTextListBlockConditionHolder(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public MobileTextListBlockCondition getCondition() {
            return condition;
        }

    }

}
