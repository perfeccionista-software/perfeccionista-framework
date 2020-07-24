package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.Condition;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebTextTableRowCondition extends Condition {

    WebTextTableRowCondition and(@NotNull WebTextTableRowCondition condition);

    WebTextTableRowCondition or(@NotNull WebTextTableRowCondition condition);

    WebTextTableRowCondition inverse();

    Deque<WebTextTableRowConditionHolder> getChildConditions();

    @NotNull WebFilterResult process(@NotNull WebTextTable element, @Nullable String hash);

}
