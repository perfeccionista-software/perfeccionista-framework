package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.Condition;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextBlockConditionHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebTextTableRowCondition extends Condition {

    WebTextTableRowCondition and(@NotNull WebTextTableRowCondition condition);

    WebTextTableRowCondition or(@NotNull WebTextTableRowCondition condition);

    WebTextTableRowCondition inverse();

    Deque<WebTextBlockConditionHolder> getChildConditions();

    @NotNull WebConditionProcessingResult process(@NotNull WebTextList element, @Nullable String hash);

}
