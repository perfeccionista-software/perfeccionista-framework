package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.list.WebListBlockCondition;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebListFilter implements Filter<WebList, Integer> {

    private final Deque<JsBlockConditionHolder> conditions = new ArrayDeque<>();

    public WebListFilter() {
    }

    public WebListFilter add(WebListBlockCondition condition) {
        this.conditions.addLast(new JsBlockConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebListFilter subtract(WebListBlockCondition condition) {
        this.conditions.addLast(new JsBlockConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public SingleResult<Integer> singleResult(WebList inputData) throws PerfeccionistaException {
        return null;
    }

    @Override
    public MultipleResult<Integer> multipleResult(WebList inputData) throws PerfeccionistaException {
        return null;
    }

    private static class JsBlockConditionHolder {

        private final ConditionUsage usage;
        private final WebListBlockCondition condition;

        public JsBlockConditionHolder(ConditionUsage usage, WebListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public ConditionUsage getUsage() {
            return usage;
        }

        public WebListBlockCondition getCondition() {
            return condition;
        }

    }

}
