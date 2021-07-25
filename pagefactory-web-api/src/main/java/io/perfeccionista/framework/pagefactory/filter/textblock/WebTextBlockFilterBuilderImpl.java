package io.perfeccionista.framework.pagefactory.filter.textblock;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.FilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.textblock.condition.WebTextBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

import static io.perfeccionista.framework.Web.allTextBlocks;

public class WebTextBlockFilterBuilderImpl implements WebTextBlockFilterBuilder {

    private final Deque<WebTextListBlockFilterResultGroupingHolder> conditions = new ArrayDeque<>();

    private WebTextBlockFilterBuilderImpl() {
    }

    public static WebTextBlockFilterBuilder webTextBlockFilterBuilderWith(@NotNull WebTextBlockCondition condition) {
        return new WebTextBlockFilterBuilderImpl()
                .add(condition);
    }

    public static WebTextBlockFilterBuilder webTextBlockFilterBuilderWithout(@NotNull WebTextBlockCondition condition) {
        return new WebTextBlockFilterBuilderImpl()
                .add(allTextBlocks())
                .subtract(condition);
    }

    public WebTextBlockFilterBuilder add(@NotNull WebTextBlockCondition condition) {
        this.conditions.addLast(WebTextListBlockFilterResultGroupingHolder.of(FilterResultGrouping.ADD, condition));
        return this;
    }

    public WebTextBlockFilterBuilder subtract(@NotNull WebTextBlockCondition condition) {
        this.conditions.addLast(WebTextListBlockFilterResultGroupingHolder.of(FilterResultGrouping.SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull WebTextBlockFilter build(@NotNull WebTextList element) {
        return WebTextBlockFilterImpl.of(element, this);
    }

    public Deque<WebTextListBlockFilterResultGroupingHolder> getConditions() {
        return conditions;
    }

}
