package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;

import java.util.Deque;

public interface WebTableFilter extends WebFilter<WebTable, WebTableFilterResult> {

    WebTableFilter add(WebTableCellCondition condition);

    WebTableFilter subtract(WebTableCellCondition condition);

    @Override
    WebTableFilterResult filter(WebTable element);

    Deque<JsTableRowConditionHolder> getConditions();

    class JsTableRowConditionHolder {

        private final ConditionUsage usage;
        private final WebTableCellCondition condition;

        public JsTableRowConditionHolder(ConditionUsage usage, WebTableCellCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public ConditionUsage getUsage() {
            return usage;
        }

        public WebTableCellCondition getCondition() {
            return condition;
        }

    }

}
