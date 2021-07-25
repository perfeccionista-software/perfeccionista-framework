package io.perfeccionista.framework.pagefactory.filter.block;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.block.condition.WebBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebBlockFilterBuilder<T extends WebBlock> extends WebFilterBuilder<WebList<T>, WebBlockFilter<T>> {

    @Override
    @NotNull WebBlockFilter<T> build(@NotNull WebList<T> element);

    WebBlockFilterBuilder<T> add(@NotNull WebBlockCondition<T> condition);

    WebBlockFilterBuilder<T> subtract(@NotNull WebBlockCondition<T> condition);

    Deque<WebListBlockFilterResultGroupingHolder<T>> getConditions();

    class WebListBlockFilterResultGroupingHolder<T extends WebBlock> {

        private final FilterResultGrouping usage;
        private final WebBlockCondition<T> condition;

        private WebListBlockFilterResultGroupingHolder(FilterResultGrouping usage, WebBlockCondition<T> condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static <T extends WebBlock> WebListBlockFilterResultGroupingHolder<T> of(FilterResultGrouping usage, WebBlockCondition<T> condition) {
            return new WebListBlockFilterResultGroupingHolder<>(usage, condition);
        }

        public FilterResultGrouping getUsage() {
            return usage;
        }

        public WebBlockCondition<T> getCondition() {
            return condition;
        }

    }

}
