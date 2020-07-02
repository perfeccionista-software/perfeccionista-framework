package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;

import java.util.Deque;

public interface WebListFilter extends WebFilter<WebList, WebListFilterResult> {

    WebListFilter add(WebListBlockCondition condition);

    WebListFilter subtract(WebListBlockCondition condition);

    @Override
    WebListFilterResult filter(WebList element);

    Deque<WebListBlockConditionHolder> getConditions();

    class WebListBlockConditionHolder {

        private final ConditionUsage usage;
        private final WebListBlockCondition condition;

        private WebListBlockConditionHolder(ConditionUsage usage, WebListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebListBlockConditionHolder of(ConditionUsage usage, WebListBlockCondition condition) {
            return new WebListBlockConditionHolder(usage, condition);
        }

        public ConditionUsage getUsage() {
            return usage;
        }

        public WebListBlockCondition getCondition() {
            return condition;
        }

    }

}
