package io.perfeccionista.framework.pagefactory.filter.texttable.condition;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebTextTableRowCondition {

    WebTextTableRowCondition and(@NotNull WebTextTableRowCondition condition);

    WebTextTableRowCondition or(@NotNull WebTextTableRowCondition condition);

    Deque<WebTextTableRowConditionHolder> getChildConditions();

    @NotNull FilterResult process(@NotNull WebTextTable element, @Nullable String hash);

    class WebTextTableRowConditionHolder {

        private final ConditionGrouping usage;
        private final WebTextTableRowCondition condition;

        public WebTextTableRowConditionHolder(ConditionGrouping usage, WebTextTableRowCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebTextTableRowConditionHolder of(ConditionGrouping usage, WebTextTableRowCondition condition) {
            return new WebTextTableRowConditionHolder(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public WebTextTableRowCondition getCondition() {
            return condition;
        }

    }

}
