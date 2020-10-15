package io.perfeccionista.framework.pagefactory.filter.textlist.condition;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebTextListBlockCondition {

    WebTextListBlockCondition and(@NotNull WebTextListBlockCondition condition);

    WebTextListBlockCondition or(@NotNull WebTextListBlockCondition condition);

    Deque<WebTextListBlockConditionHolder> getChildConditions();

    @NotNull WebFilterResult process(@NotNull WebTextList element, @Nullable String hash);

    class WebTextListBlockConditionHolder {

        private final WebConditionGrouping usage;
        private final WebTextListBlockCondition condition;

        private WebTextListBlockConditionHolder(WebConditionGrouping usage, WebTextListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebTextListBlockConditionHolder of(WebConditionGrouping usage, WebTextListBlockCondition condition) {
            return new WebTextListBlockConditionHolder(usage, condition);
        }

        public WebConditionGrouping getUsage() {
            return usage;
        }

        public WebTextListBlockCondition getCondition() {
            return condition;
        }

    }

}
