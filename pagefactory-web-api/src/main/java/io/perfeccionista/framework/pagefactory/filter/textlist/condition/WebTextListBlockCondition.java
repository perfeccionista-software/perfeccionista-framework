package io.perfeccionista.framework.pagefactory.filter.textlist.condition;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebTextListBlockCondition {

    WebTextListBlockCondition and(@NotNull WebTextListBlockCondition condition);

    WebTextListBlockCondition or(@NotNull WebTextListBlockCondition condition);

    Deque<WebTextListBlockConditionHolder> getChildConditions();

    @NotNull FilterResult process(@NotNull WebTextList element, @Nullable String hash);

    class WebTextListBlockConditionHolder {

        private final ConditionGrouping usage;
        private final WebTextListBlockCondition condition;

        private WebTextListBlockConditionHolder(ConditionGrouping usage, WebTextListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebTextListBlockConditionHolder of(ConditionGrouping usage, WebTextListBlockCondition condition) {
            return new WebTextListBlockConditionHolder(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public WebTextListBlockCondition getCondition() {
            return condition;
        }

    }

}
