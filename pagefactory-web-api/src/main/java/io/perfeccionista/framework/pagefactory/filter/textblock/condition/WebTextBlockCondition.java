package io.perfeccionista.framework.pagefactory.filter.textblock.condition;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebTextBlockCondition {

    WebTextBlockCondition and(@NotNull WebTextBlockCondition condition);

    WebTextBlockCondition or(@NotNull WebTextBlockCondition condition);

    Deque<WebTextListBlockConditionHolder> getChildConditions();

    @NotNull FilterResult process(@NotNull WebTextList element, @Nullable String hash);

    class WebTextListBlockConditionHolder {

        private final ConditionGrouping usage;
        private final WebTextBlockCondition condition;

        private WebTextListBlockConditionHolder(ConditionGrouping usage, WebTextBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebTextListBlockConditionHolder of(ConditionGrouping usage, WebTextBlockCondition condition) {
            return new WebTextListBlockConditionHolder(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public WebTextBlockCondition getCondition() {
            return condition;
        }

    }

}
