package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.texttable.condition.WebTextTableRowCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebTextTableFilterBuilder extends WebFilterBuilder<WebTextTable, WebTextTableFilter> {

    @Override
    @NotNull WebTextTableFilter build(@NotNull WebTextTable element);

    WebTextTableFilterBuilder add(@NotNull WebTextTableRowCondition condition);

    WebTextTableFilterBuilder subtract(@NotNull WebTextTableRowCondition condition);

    Deque<WebTextTableRowFilterResultGroupingHolder> getConditions();

    class WebTextTableRowFilterResultGroupingHolder {

        private final WebFilterResultGrouping usage;
        private final WebTextTableRowCondition condition;

        public WebTextTableRowFilterResultGroupingHolder(WebFilterResultGrouping usage, WebTextTableRowCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebTextTableRowFilterResultGroupingHolder of(WebFilterResultGrouping usage, WebTextTableRowCondition condition) {
            return new WebTextTableRowFilterResultGroupingHolder(usage, condition);
        }

        public WebFilterResultGrouping getUsage() {
            return usage;
        }

        public WebTextTableRowCondition getCondition() {
            return condition;
        }

    }

}
