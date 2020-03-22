package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.exceptions.base.PerfeccionistaException;
import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonCondition;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebRadioButtonFilter implements Filter<WebRadioGroup, Integer> {

    private final Deque<JsRadioButtonConditionHolder> conditions = new ArrayDeque<>();

    public WebRadioButtonFilter() {
        // TODO: пустое условие, которое возвращает все элементы
    }

    public WebRadioButtonFilter(WebRadioButtonCondition stringCondition) {
        this.conditions.addLast(new JsRadioButtonConditionHolder(ConditionUsage.ADD, stringCondition));
    }

    public WebRadioButtonFilter add(WebRadioButtonCondition stringCondition) {
        this.conditions.addLast(new JsRadioButtonConditionHolder(ConditionUsage.ADD, stringCondition));
        return this;
    }

    public WebRadioButtonFilter substract(WebRadioButtonCondition stringCondition) {
        this.conditions.addLast(new JsRadioButtonConditionHolder(ConditionUsage.SUBTRACT, stringCondition));
        return this;
    }

    @Override
    public SingleResult<Integer> singleResult(WebRadioGroup inputData) throws PerfeccionistaException {
        return null;
    }

    @Override
    public MultipleResult<Integer> multipleResult(WebRadioGroup inputData) throws PerfeccionistaException {
        return null;
    }

    private static class JsRadioButtonConditionHolder {

        private final ConditionUsage usage;
        private final WebRadioButtonCondition condition;

        public JsRadioButtonConditionHolder(ConditionUsage usage, WebRadioButtonCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public ConditionUsage getUsage() {
            return usage;
        }

        public WebRadioButtonCondition getCondition() {
            return condition;
        }

    }

}
