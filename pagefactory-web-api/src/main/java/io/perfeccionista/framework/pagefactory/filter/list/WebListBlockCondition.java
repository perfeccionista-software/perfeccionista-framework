package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.Condition;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebListBlockCondition extends Condition {

    WebListBlockCondition and(@NotNull WebListBlockCondition condition);

    WebListBlockCondition or(@NotNull WebListBlockCondition condition);

    WebListBlockCondition inverse();

    Deque<WebListBlockConditionHolder> getChildConditions();

    @NotNull WebConditionProcessingResult process(@NotNull WebList element, @Nullable String hash);

}
