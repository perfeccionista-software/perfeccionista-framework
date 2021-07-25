package io.perfeccionista.framework.pagefactory.filter.textblock;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebTextBlockFilterBuilder extends WebFilterBuilder<WebTextList, WebTextBlockFilter> {

    @Override
    @NotNull WebTextBlockFilter build(@NotNull WebTextList element);

    WebTextBlockFilterBuilder add(@NotNull WebTextBlockCondition condition);

    WebTextBlockFilterBuilder subtract(@NotNull WebTextBlockCondition condition);

    Deque<WebTextListBlockFilterResultGroupingHolder> getConditions();

    class WebTextListBlockFilterResultGroupingHolder {

        private final FilterResultGrouping usage;
        private final WebTextBlockCondition condition;

        private WebTextListBlockFilterResultGroupingHolder(FilterResultGrouping usage, WebTextBlockCondition condition) {
            this.usage = usage;
            this.condition = condition;
        }

        public static WebTextListBlockFilterResultGroupingHolder of(FilterResultGrouping usage, WebTextBlockCondition condition) {
            return new WebTextListBlockFilterResultGroupingHolder(usage, condition);
        }

        public FilterResultGrouping getUsage() {
            return usage;
        }

        public WebTextBlockCondition getCondition() {
            return condition;
        }

    }

}
