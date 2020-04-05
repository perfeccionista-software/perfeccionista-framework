package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableCellCondition;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTableFilter implements Filter<WebTable, Integer> {

    private final Deque<JsTableRowConditionHolder> conditions = new ArrayDeque<>();

    public WebTableFilter() {
    }

    public WebTableFilter add(WebTableCellCondition condition) {
        this.conditions.addLast(new JsTableRowConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebTableFilter subtract(WebTableCellCondition condition) {
        this.conditions.addLast(new JsTableRowConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public SingleResult<Integer> singleResult(WebTable inputData) throws PerfeccionistaException {
        return null;
    }

    @Override
    public MultipleResult<Integer> multipleResult(WebTable inputData) throws PerfeccionistaException {
        return null;
    }

    private static class JsTableRowConditionHolder {

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
