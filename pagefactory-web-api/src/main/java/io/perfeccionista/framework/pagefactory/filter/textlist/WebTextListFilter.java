package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import io.perfeccionista.framework.pagefactory.filter.Filter;

import java.util.Deque;

public interface WebTextListFilter extends Filter<WebTextList, WebTextListFilterResult> {

    WebTextListFilter add(WebTextListBlockCondition condition);

    WebTextListFilter subtract(WebTextListBlockCondition condition);

    @Override
    WebTextListFilterResult filter(WebTextList element);

    Deque<JsStringBlockConditionHolder> getConditions();

    class JsStringBlockConditionHolder {

        private final ConditionUsage usage;
        private final WebTextListBlockCondition condition;

        public JsStringBlockConditionHolder(ConditionUsage usage, WebTextListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public ConditionUsage getUsage() {
            return usage;
        }

        public WebTextListBlockCondition getCondition() {
            return condition;
        }

    }

}
