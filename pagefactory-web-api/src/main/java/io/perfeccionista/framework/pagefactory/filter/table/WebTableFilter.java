package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebTableFilter extends WebFilter<WebTable, WebTableFilterResult> {

    WebTableFilter add(@NotNull WebTableRowCondition condition);

    WebTableFilter subtract(@NotNull WebTableRowCondition condition);

    @Override
    @NotNull WebTableFilterResult filter(@NotNull WebTable element);

    Deque<WebTableRowConditionHolder> getConditions();

}
