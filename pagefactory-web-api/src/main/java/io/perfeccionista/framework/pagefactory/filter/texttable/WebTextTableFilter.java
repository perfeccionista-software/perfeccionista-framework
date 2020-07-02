package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;

import java.util.Deque;

public interface WebTextTableFilter extends WebFilter<WebTextTable, WebTextTableFilterResult> {

    WebTextTableFilter add(WebTextTableCellCondition condition);

    WebTextTableFilter subtract(WebTextTableCellCondition condition);

    @Override
    WebTextTableFilterResult filter(WebTextTable element);

    Deque<JsStringTableRowConditionHolder> getConditions();

    class JsStringTableRowConditionHolder {

        private final ConditionUsage usage;
        private final WebTextTableCellCondition condition;

        public JsStringTableRowConditionHolder(ConditionUsage usage, WebTextTableCellCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public ConditionUsage getUsage() {
            return usage;
        }

        public WebTextTableCellCondition getCondition() {
            return condition;
        }

    }

}
