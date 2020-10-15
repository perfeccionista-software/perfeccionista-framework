package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResultGrouping;
import io.perfeccionista.framework.pagefactory.filter.textlist.condition.WebTextListBlockCondition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTextListFilterBuilderImpl implements WebTextListFilterBuilder {

    private final Deque<WebTextListBlockFilterResultGroupingHolder> conditions = new ArrayDeque<>();

    private WebTextListFilterBuilderImpl() {
    }

    public static WebTextListFilterBuilderImpl webTextListFilterBuilder() {
        return new WebTextListFilterBuilderImpl();
    }

    public WebTextListFilterBuilder add(@NotNull WebTextListBlockCondition condition) {
        this.conditions.addLast(WebTextListBlockFilterResultGroupingHolder.of(WebFilterResultGrouping.ADD, condition));
        return this;
    }

    public WebTextListFilterBuilder subtract(@NotNull WebTextListBlockCondition condition) {
        this.conditions.addLast(WebTextListBlockFilterResultGroupingHolder.of(WebFilterResultGrouping.SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull WebTextListFilter build(@NotNull WebTextList element) {
        return WebTextListFilterImpl.of(element, this);
    }

    public Deque<WebTextListBlockFilterResultGroupingHolder> getConditions() {
        return conditions;
    }

}
