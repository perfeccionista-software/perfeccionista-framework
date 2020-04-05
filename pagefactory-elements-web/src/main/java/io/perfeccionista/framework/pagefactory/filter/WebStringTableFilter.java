package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.pagefactory.elements.WebStringTable;
import io.perfeccionista.framework.pagefactory.filter.stringtable.WebStringTableCellCondition;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebStringTableFilter implements Filter<WebStringTable, Integer> {

    private final Deque<JsStringTableRowConditionHolder> conditions = new ArrayDeque<>();

    public WebStringTableFilter() {
    }

//    public static WebStringTableFilter withWebStringTableRowTextValue(String columnName, StringValue value) {
//        return new WebStringTableFilter().add(new WebStringTableRowTextValueCondition(columnName, value));
//    }
//
//    public static WebStringTableFilter withoutWebStringTableRowTextValue(String columnName, StringValue value) {
//        return new WebStringTableFilter().subtract(new WebStringTableRowTextValueCondition(columnName, value));
//    }
//
//    public static WebStringTableFilter withWebStringTableRowTextValue(String columnName, NumberValue<? extends Number> value) {
//        return new WebStringTableFilter().add(new WebStringTableRowTextValueCondition(columnName, value));
//    }
//
//    public static WebStringTableFilter withoutWebStringTableRowTextValue(String columnName, NumberValue<? extends Number> value) {
//        return new WebStringTableFilter().subtract(new WebStringTableRowTextValueCondition(columnName, value));
//    }
//
//    public static WebStringTableFilter withWebStringTableRowIndex(NumberValue<Integer> value) {
//        return new WebStringTableFilter().add(new WebStringTableRowIndexCondition(value));
//    }
//
//    public static WebStringTableFilter withoutWebStringTableRowIndex(NumberValue<Integer> value) {
//        return new WebStringTableFilter().subtract(new WebStringTableRowIndexCondition(value));
//    }

    public WebStringTableFilter add(WebStringTableCellCondition condition) {
        this.conditions.addLast(new JsStringTableRowConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebStringTableFilter subtract(WebStringTableCellCondition condition) {
        this.conditions.addLast(new JsStringTableRowConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public SingleResult<Integer> singleResult(WebStringTable inputData) throws PerfeccionistaException {
        return null;
    }

    @Override
    public MultipleResult<Integer> multipleResult(WebStringTable inputData) throws PerfeccionistaException {
        return null;
    }

    private static class JsStringTableRowConditionHolder {

        private final ConditionUsage usage;
        private final WebStringTableCellCondition condition;

        public JsStringTableRowConditionHolder(ConditionUsage usage, WebStringTableCellCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public ConditionUsage getUsage() {
            return usage;
        }

        public WebStringTableCellCondition getCondition() {
            return condition;
        }

    }

}
