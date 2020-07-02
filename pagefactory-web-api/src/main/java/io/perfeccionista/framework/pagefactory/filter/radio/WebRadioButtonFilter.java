package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;

import java.util.Deque;

public interface WebRadioButtonFilter extends WebFilter<WebRadioGroup, WebRadioButtonFilterResult> {

    WebRadioButtonFilter add(WebRadioButtonCondition condition);

    WebRadioButtonFilter subtract(WebRadioButtonCondition condition);

    @Override
    WebRadioButtonFilterResult filter(WebRadioGroup element);

    Deque<JsRadioButtonConditionHolder> getConditions();

    class JsRadioButtonConditionHolder {

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
