package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.list.condition.WebListBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebListFilterBuilder extends WebFilterBuilder<WebList, WebListFilter> {

    @Override
    @NotNull WebListFilter build(@NotNull WebList element);

    WebListFilterBuilder add(@NotNull WebListBlockCondition condition);

    WebListFilterBuilder subtract(@NotNull WebListBlockCondition condition);

    Deque<WebListBlockFilterResultGroupingHolder> getConditions();

    class WebListBlockFilterResultGroupingHolder {

        private final WebFilterResultGrouping usage;
        private final WebListBlockCondition condition;

        private WebListBlockFilterResultGroupingHolder(WebFilterResultGrouping usage, WebListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebListBlockFilterResultGroupingHolder of(WebFilterResultGrouping usage, WebListBlockCondition condition) {
            return new WebListBlockFilterResultGroupingHolder(usage, condition);
        }

        public WebFilterResultGrouping getUsage() {
            return usage;
        }

        public WebListBlockCondition getCondition() {
            return condition;
        }

    }

}
