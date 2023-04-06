package io.perfeccionista.framework.pagefactory.filter.conditions;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebItemCondition<T extends WebBlock<?>> {

    WebItemCondition<T> and(@NotNull WebItemCondition<T> condition);

    WebItemCondition<T> or(@NotNull WebItemCondition<T> condition);

    Deque<WebListItemConditionHolder<T>> getChildConditions();

    @NotNull FilterResult process(@NotNull WebList<T> element, @Nullable String hash);

    class WebListItemConditionHolder<T extends WebBlock<?>> {

        private final ConditionGrouping usage;
        private final WebItemCondition<T> condition;

        private WebListItemConditionHolder(ConditionGrouping usage, WebItemCondition<T> condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static <T extends WebBlock<?>> WebListItemConditionHolder<T> of(ConditionGrouping usage, WebItemCondition<T> condition) {
            return new WebListItemConditionHolder<>(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public WebItemCondition<T> getCondition() {
            return condition;
        }

    }

}
