package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebTextListFilterBuilder extends WebFilterBuilder<WebTextList, WebTextListFilter> {

    @Override
    @NotNull WebTextListFilter build(@NotNull WebTextList element);

    WebTextListFilterBuilder add(@NotNull WebTextListBlockCondition condition);

    WebTextListFilterBuilder subtract(@NotNull WebTextListBlockCondition condition);

    Deque<WebTextListBlockFilterResultGroupingHolder> getConditions();

    class WebTextListBlockFilterResultGroupingHolder {

        private final WebFilterResultGrouping usage;
        private final WebTextListBlockCondition condition;

        private WebTextListBlockFilterResultGroupingHolder(WebFilterResultGrouping usage, WebTextListBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebTextListBlockFilterResultGroupingHolder of(WebFilterResultGrouping usage, WebTextListBlockCondition condition) {
            return new WebTextListBlockFilterResultGroupingHolder(usage, condition);
        }

        public WebFilterResultGrouping getUsage() {
            return usage;
        }

        public WebTextListBlockCondition getCondition() {
            return condition;
        }

    }

}
