package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.filter.Condition;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter.WebListBlockConditionHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebListBlockCondition extends Condition {

    WebListBlockCondition and(WebListBlockCondition condition);

    WebListBlockCondition or(WebListBlockCondition condition);

    Deque<WebListBlockConditionHolder> getChildConditions();

    WebConditionProcessingResult process(@NotNull WebList element, @Nullable String hash);

}
