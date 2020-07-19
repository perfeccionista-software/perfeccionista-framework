package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebTextTableFilterBuilder extends WebFilterBuilder<WebTextTable, WebTextTableFilter> {

    @Override
    @NotNull WebTextTableFilter build(@NotNull WebTextTable element);

    WebTextTableFilterBuilder add(@NotNull WebTextTableRowCondition condition);

    WebTextTableFilterBuilder subtract(@NotNull WebTextTableRowCondition condition);

    Deque<WebTextTableRowConditionHolder> getConditions();

}
