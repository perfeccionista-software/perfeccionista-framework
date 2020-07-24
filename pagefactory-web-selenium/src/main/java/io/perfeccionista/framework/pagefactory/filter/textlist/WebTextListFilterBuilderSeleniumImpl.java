package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.ConditionUsage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebTextListFilterBuilderSeleniumImpl implements WebTextListFilterBuilder {

    private final Deque<WebTextListBlockConditionHolder> conditions = new ArrayDeque<>();

    public WebTextListFilterBuilder add(@NotNull WebTextListBlockCondition condition) {
        this.conditions.addLast(WebTextListBlockConditionHolder.of(ConditionUsage.ADD, condition));
        return this;
    }

    public WebTextListFilterBuilder subtract(@NotNull WebTextListBlockCondition condition) {
        this.conditions.addLast(WebTextListBlockConditionHolder.of(ConditionUsage.SUBTRACT, condition));
        return this;
    }

    @Override
    public @NotNull WebTextListFilter build(@NotNull WebTextList element) {
        return new WebTextListFilterSeleniumImpl(element, this);
    }

    public Deque<WebTextListBlockConditionHolder> getConditions() {
        return conditions;
    }

}
