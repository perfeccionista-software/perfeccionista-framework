package io.perfeccionista.framework.pagefactory.filter.list.condition;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.ConditionGrouping;
import io.perfeccionista.framework.pagefactory.filter.FilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebListBlockCondition<T extends WebBlock> {

    WebListBlockCondition<T> and(@NotNull WebListBlockCondition<T> condition);

    WebListBlockCondition<T> or(@NotNull WebListBlockCondition<T> condition);

    Deque<WebListBlockConditionHolder<T>> getChildConditions();

    @NotNull FilterResult process(@NotNull WebList<T> element, @Nullable String hash);

    class WebListBlockConditionHolder<T extends WebBlock> {

        private final ConditionGrouping usage;
        private final WebListBlockCondition<T> condition;

        private WebListBlockConditionHolder(ConditionGrouping usage, WebListBlockCondition<T> condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static <T extends WebBlock> WebListBlockConditionHolder<T> of(ConditionGrouping usage, WebListBlockCondition<T> condition) {
            return new WebListBlockConditionHolder<>(usage, condition);
        }

        public ConditionGrouping getUsage() {
            return usage;
        }

        public WebListBlockCondition<T> getCondition() {
            return condition;
        }

    }

}
