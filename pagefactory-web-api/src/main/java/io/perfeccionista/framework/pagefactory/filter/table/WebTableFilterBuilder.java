package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebTableFilterBuilder extends WebFilterBuilder<WebTable, WebTableFilter> {

    @Override
    @NotNull WebTableFilter build(@NotNull WebTable element);

    WebTableFilterBuilder add(@NotNull WebTableRowCondition condition);

    WebTableFilterBuilder subtract(@NotNull WebTableRowCondition condition);

    Deque<WebTableRowConditionHolder> getConditions();

}
