package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebTextListFilter extends WebFilter<WebTextList, WebTextListFilterResult> {

    WebTextListFilter add(@NotNull WebTextListBlockCondition condition);

    WebTextListFilter subtract(@NotNull WebTextListBlockCondition condition);

    @Override
    @NotNull WebTextListFilterResult filter(@NotNull WebTextList element);

    Deque<WebTextBlockConditionHolder> getConditions();

}
