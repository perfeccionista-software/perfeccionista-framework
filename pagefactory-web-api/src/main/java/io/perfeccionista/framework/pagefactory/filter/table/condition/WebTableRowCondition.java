package io.perfeccionista.framework.pagefactory.filter.table.condition;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebTableRowCondition {

    WebTableRowCondition and(@NotNull WebTableRowCondition condition);

    WebTableRowCondition or(@NotNull WebTableRowCondition condition);

    Deque<WebTableRowConditionHolder> getChildConditions();

    @NotNull FilterResult process(@NotNull WebTable element, @Nullable String hash);

    class WebTableRowConditionHolder {

        private final ConditionGrouping usage;
        private final WebTableRowCondition condition;

        private WebTableRowConditionHolder(ConditionGrouping usage, WebTableRowCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebTableRowConditionHolder of(ConditionGrouping usage, WebTableRowCondition condition) {
            return new WebTableRowConditionHolder(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public WebTableRowCondition getCondition() {
            return condition;
        }

    }

}
