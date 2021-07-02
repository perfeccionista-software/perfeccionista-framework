package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebListFilterBuilder<T extends WebBlock> extends WebFilterBuilder<WebList<T>, WebListFilter<T>> {

    @Override
    @NotNull WebListFilter<T> build(@NotNull WebList<T> element);

    WebListFilterBuilder<T> add(@NotNull WebListBlockCondition<T> condition);

    WebListFilterBuilder<T> subtract(@NotNull WebListBlockCondition<T> condition);

    Deque<WebListBlockFilterResultGroupingHolder<T>> getConditions();

    class WebListBlockFilterResultGroupingHolder<T extends WebBlock> {

        private final FilterResultGrouping usage;
        private final WebListBlockCondition<T> condition;

        private WebListBlockFilterResultGroupingHolder(FilterResultGrouping usage, WebListBlockCondition<T> condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static <T extends WebBlock> WebListBlockFilterResultGroupingHolder<T> of(FilterResultGrouping usage, WebListBlockCondition<T> condition) {
            return new WebListBlockFilterResultGroupingHolder<>(usage, condition);
        }

        public FilterResultGrouping getUsage() {
            return usage;
        }

        public WebListBlockCondition<T> getCondition() {
            return condition;
        }

    }

}
