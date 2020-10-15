package io.perfeccionista.framework.pagefactory.filter.radio.condition;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.WebConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebRadioButtonCondition {

    WebRadioButtonCondition and(@NotNull WebRadioButtonCondition condition);

    WebRadioButtonCondition or(@NotNull WebRadioButtonCondition condition);

    Deque<WebRadioButtonConditionHolder> getChildConditions();

    @NotNull WebFilterResult process(@NotNull WebRadioGroup element, @Nullable String hash);

    class WebRadioButtonConditionHolder {

        private final WebConditionGrouping usage;
        private final WebRadioButtonCondition condition;

        private WebRadioButtonConditionHolder(WebConditionGrouping usage, WebRadioButtonCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebRadioButtonConditionHolder of(WebConditionGrouping usage, WebRadioButtonCondition condition) {
            return new WebRadioButtonConditionHolder(usage, condition);
        }

        public WebConditionGrouping getUsage() {
            return usage;
        }

        public WebRadioButtonCondition getCondition() {
            return condition;
        }

    }

}
