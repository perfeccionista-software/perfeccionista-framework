package io.perfeccionista.framework.pagefactory.filter.list.condition;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebListBlockCondition {

    WebListBlockCondition and(@NotNull WebListBlockCondition condition);

    WebListBlockCondition or(@NotNull WebListBlockCondition condition);

    Deque<WebListBlockConditionHolder> getChildConditions();

    @NotNull WebFilterResult process(@NotNull WebList element, @Nullable String hash);

    class WebListBlockConditionHolder {

        private final WebConditionGrouping usage;
        private final WebListBlockCondition condition;

        private WebListBlockConditionHolder(WebConditionGrouping usage, WebListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebListBlockConditionHolder of(WebConditionGrouping usage, WebListBlockCondition condition) {
            return new WebListBlockConditionHolder(usage, condition);
        }

        public WebConditionGrouping getUsage() {
            return usage;
        }

        public WebListBlockCondition getCondition() {
            return condition;
        }

    }

}
