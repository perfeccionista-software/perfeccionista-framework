package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.radio.WebRadioButtonCondition;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebRadioButtonFilter implements Filter<WebRadioGroup, Integer> {

    private final Deque<JsRadioButtonConditionHolder> conditions = new ArrayDeque<>();

    public WebRadioButtonFilter() {
    }

    public WebRadioButtonFilter add(WebRadioButtonCondition condition) {
        this.conditions.addLast(new JsRadioButtonConditionHolder(ConditionUsage.ADD, condition));
        return this;
    }

    public WebRadioButtonFilter subtract(WebRadioButtonCondition condition) {
        this.conditions.addLast(new JsRadioButtonConditionHolder(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public SingleResult<Integer> singleResult(WebRadioGroup inputData) {
        return null;
    }

    @Override
    public MultipleResult<Integer> multipleResult(WebRadioGroup inputData) {
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
