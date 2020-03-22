package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.pagefactory.elements.WebUnorderedList;
import io.perfeccionista.framework.pagefactory.filter.block.WebBlockCondition;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebBlockFilter implements Filter<WebUnorderedList, Integer> {

    private final Deque<JsBlockConditionHolder> conditions = new ArrayDeque<>();

    public WebBlockFilter() {
        // TODO: пустое условие, которое возвращает все элементы
    }

    public WebBlockFilter(WebBlockCondition stringCondition) {
        this.conditions.addLast(new JsBlockConditionHolder(ConditionUsage.ADD, stringCondition));
    }

    public WebBlockFilter add(WebBlockCondition stringCondition) {
        this.conditions.addLast(new JsBlockConditionHolder(ConditionUsage.ADD, stringCondition));
        return this;
    }

    public WebBlockFilter substract(WebBlockCondition stringCondition) {
        this.conditions.addLast(new JsBlockConditionHolder(ConditionUsage.SUBTRACT, stringCondition));
        return this;
    }

    @Override
    public SingleResult<Integer> singleResult(WebUnorderedList inputData) throws PerfeccionistaException {
        return null;
    }

    @Override
    public MultipleResult<Integer> multipleResult(WebUnorderedList inputData) throws PerfeccionistaException {
        return null;
    }

    private static class JsBlockConditionHolder {

        private final ConditionUsage usage;
        private final WebBlockCondition condition;

        public JsBlockConditionHolder(ConditionUsage usage, WebBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public ConditionUsage getUsage() {
            return usage;
        }

        public WebBlockCondition getCondition() {
            return condition;
        }

    }

}
