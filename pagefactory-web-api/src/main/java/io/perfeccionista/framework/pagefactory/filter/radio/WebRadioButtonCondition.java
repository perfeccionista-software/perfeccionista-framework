package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.Condition;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableRowConditionHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebRadioButtonCondition extends Condition {

    WebRadioButtonCondition and(@NotNull WebRadioButtonCondition condition);

    WebRadioButtonCondition or(@NotNull WebRadioButtonCondition condition);

    WebRadioButtonCondition inverse();

    Deque<WebTableRowConditionHolder> getChildConditions();

    @NotNull WebConditionProcessingResult process(@NotNull WebTable element, @Nullable String hash);

}
