package io.perfeccionista.framework.pagefactory.filter.block.condition;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebBlockCondition<T extends WebBlock> {

    WebBlockCondition<T> and(@NotNull WebBlockCondition<T> condition);

    WebBlockCondition<T> or(@NotNull WebBlockCondition<T> condition);

    Deque<WebListBlockConditionHolder<T>> getChildConditions();

    @NotNull FilterResult process(@NotNull WebList<T> element, @Nullable String hash);

    class WebListBlockConditionHolder<T extends WebBlock> {

        private final ConditionGrouping usage;
        private final WebBlockCondition<T> condition;

        private WebListBlockConditionHolder(ConditionGrouping usage, WebBlockCondition<T> condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static <T extends WebBlock> WebListBlockConditionHolder<T> of(ConditionGrouping usage, WebBlockCondition<T> condition) {
            return new WebListBlockConditionHolder<>(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public WebBlockCondition<T> getCondition() {
            return condition;
        }

    }

}
