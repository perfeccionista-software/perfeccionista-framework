package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebListFilter extends WebFilter<WebList, WebListFilterResult> {

    WebListFilter add(@NotNull WebListBlockCondition condition);

    WebListFilter subtract(@NotNull WebListBlockCondition condition);

    @Override
    @NotNull WebListFilterResult filter(@NotNull WebList element);

    Deque<WebListBlockConditionHolder> getConditions();

}
