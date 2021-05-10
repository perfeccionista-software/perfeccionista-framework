package io.perfeccionista.framework.pagefactory.filter.radio.condition;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebRadioButtonCondition {

    WebRadioButtonCondition and(@NotNull WebRadioButtonCondition condition);

    WebRadioButtonCondition or(@NotNull WebRadioButtonCondition condition);

    Deque<WebRadioButtonConditionHolder> getChildConditions();

    @NotNull FilterResult process(@NotNull WebRadioGroup element, @Nullable String hash);

    class WebRadioButtonConditionHolder {

        private final ConditionGrouping usage;
        private final WebRadioButtonCondition condition;

        private WebRadioButtonConditionHolder(ConditionGrouping usage, WebRadioButtonCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebRadioButtonConditionHolder of(ConditionGrouping usage, WebRadioButtonCondition condition) {
            return new WebRadioButtonConditionHolder(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public WebRadioButtonCondition getCondition() {
            return condition;
        }

    }

}
