package io.perfeccionista.framework.pagefactory.filter.texttable.condition;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebTextTableRowCondition {

    WebTextTableRowCondition and(@NotNull WebTextTableRowCondition condition);

    WebTextTableRowCondition or(@NotNull WebTextTableRowCondition condition);

    Deque<WebTextTableRowConditionHolder> getChildConditions();

    @NotNull WebFilterResult process(@NotNull WebTextTable element, @Nullable String hash);

    class WebTextTableRowConditionHolder {

        private final WebConditionGrouping usage;
        private final WebTextTableRowCondition condition;

        public WebTextTableRowConditionHolder(WebConditionGrouping usage, WebTextTableRowCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebTextTableRowConditionHolder of(WebConditionGrouping usage, WebTextTableRowCondition condition) {
            return new WebTextTableRowConditionHolder(usage, condition);
        }

        public WebConditionGrouping getUsage() {
            return usage;
        }

        public WebTextTableRowCondition getCondition() {
            return condition;
        }

    }

}
