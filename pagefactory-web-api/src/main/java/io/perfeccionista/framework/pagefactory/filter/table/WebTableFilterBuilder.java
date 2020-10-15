package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.table.condition.WebTableRowCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebTableFilterBuilder extends WebFilterBuilder<WebTable, WebTableFilter> {

    @Override
    @NotNull WebTableFilter build(@NotNull WebTable element);

    WebTableFilterBuilder add(@NotNull WebTableRowCondition condition);

    WebTableFilterBuilder subtract(@NotNull WebTableRowCondition condition);

    Deque<WebTableRowFilterResultGroupingHolder> getConditions();

    class WebTableRowFilterResultGroupingHolder {

        private final WebFilterResultGrouping usage;
        private final WebTableRowCondition condition;

        private WebTableRowFilterResultGroupingHolder(WebFilterResultGrouping usage, WebTableRowCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebTableRowFilterResultGroupingHolder of(WebFilterResultGrouping usage, WebTableRowCondition condition) {
            return new WebTableRowFilterResultGroupingHolder(usage, condition);
        }

        public WebFilterResultGrouping getUsage() {
            return usage;
        }

        public WebTableRowCondition getCondition() {
            return condition;
        }

    }

}
