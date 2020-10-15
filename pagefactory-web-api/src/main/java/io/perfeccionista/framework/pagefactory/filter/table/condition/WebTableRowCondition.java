package io.perfeccionista.framework.pagefactory.filter.table.condition;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebTableRowCondition {

    WebTableRowCondition and(@NotNull WebTableRowCondition condition);

    WebTableRowCondition or(@NotNull WebTableRowCondition condition);

    Deque<WebTableRowConditionHolder> getChildConditions();

    @NotNull WebFilterResult process(@NotNull WebTable element, @Nullable String hash);

    class WebTableRowConditionHolder {

        private final WebConditionGrouping usage;
        private final WebTableRowCondition condition;

        private WebTableRowConditionHolder(WebConditionGrouping usage, WebTableRowCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebTableRowConditionHolder of(WebConditionGrouping usage, WebTableRowCondition condition) {
            return new WebTableRowConditionHolder(usage, condition);
        }

        public WebConditionGrouping getUsage() {
            return usage;
        }

        public WebTableRowCondition getCondition() {
            return condition;
        }

    }

}
