package io.perfeccionista.framework.pagefactory.filter;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.conditions.WebItemCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebListFilterBuilder<T extends WebBlock<?>> {

    @NotNull WebListFilter<T> build(@NotNull WebList<T> element);

    WebListFilterBuilder<T> add(@NotNull WebItemCondition<T> condition);

    WebListFilterBuilder<T> subtract(@NotNull WebItemCondition<T> condition);

    Deque<WebListFilterResultGroupingHolder<T>> getConditions();

    class WebListFilterResultGroupingHolder<T extends WebBlock<?>> {

        private final FilterResultGrouping usage;
        private final WebItemCondition<T> condition;

        private WebListFilterResultGroupingHolder(FilterResultGrouping usage, WebItemCondition<T> condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static <T extends WebBlock<?>> WebListFilterResultGroupingHolder<T> of(FilterResultGrouping usage, WebItemCondition<T> condition) {
            return new WebListFilterResultGroupingHolder<>(usage, condition);
        }

        public FilterResultGrouping getUsage() {
            return usage;
        }

        public WebItemCondition<T> getCondition() {
            return condition;
        }

    }

}
