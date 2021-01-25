package io.perfeccionista.framework.pagefactory.filter.list.condition;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebListBlockCondition {

    WebListBlockCondition and(@NotNull WebListBlockCondition condition);

    WebListBlockCondition or(@NotNull WebListBlockCondition condition);

    Deque<WebListBlockConditionHolder> getChildConditions();

    @NotNull FilterResult process(@NotNull WebList element, @Nullable String hash);

    class WebListBlockConditionHolder {

        private final ConditionGrouping usage;
        private final WebListBlockCondition condition;

        private WebListBlockConditionHolder(ConditionGrouping usage, WebListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebListBlockConditionHolder of(ConditionGrouping usage, WebListBlockCondition condition) {
            return new WebListBlockConditionHolder(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public WebListBlockCondition getCondition() {
            return condition;
        }

    }

}
