package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.Condition;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebTableRowCondition extends Condition {

    WebTableRowCondition and(@NotNull WebTableRowCondition condition);

    WebTableRowCondition or(@NotNull WebTableRowCondition condition);

    WebTableRowCondition inverse();

    Deque<WebTableRowConditionHolder> getChildConditions();

    @NotNull WebFilterResult process(@NotNull WebTable element, @Nullable String hash);

}
