package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.radio.condition.WebRadioButtonCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebRadioGroupFilterBuilder extends WebFilterBuilder<WebRadioGroup, WebRadioGroupFilter> {

    @Override
    @NotNull WebRadioGroupFilter build(@NotNull WebRadioGroup element);

    WebRadioGroupFilterBuilder add(@NotNull WebRadioButtonCondition condition);

    WebRadioGroupFilterBuilder subtract(@NotNull WebRadioButtonCondition condition);

    Deque<WebRadioButtonFilterResultGroupingHolder> getConditions();

    class WebRadioButtonFilterResultGroupingHolder {

        private final WebFilterResultGrouping usage;
        private final WebRadioButtonCondition condition;

        private WebRadioButtonFilterResultGroupingHolder(WebFilterResultGrouping usage, WebRadioButtonCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebRadioButtonFilterResultGroupingHolder of(WebFilterResultGrouping usage, WebRadioButtonCondition condition) {
            return new WebRadioButtonFilterResultGroupingHolder(usage, condition);
        }

        public WebFilterResultGrouping getUsage() {
            return usage;
        }

        public WebRadioButtonCondition getCondition() {
            return condition;
        }

    }

}
