package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebTextTableFilter extends WebFilter<WebTextTable, WebTextTableFilterResult> {

    WebTextTableFilter add(@NotNull WebTextTableRowCondition condition);

    WebTextTableFilter subtract(@NotNull WebTextTableRowCondition condition);

    @Override
    @NotNull WebTextTableFilterResult filter(@NotNull WebTextTable element);

    Deque<WebTextTableRowConditionHolder> getConditions();

}
