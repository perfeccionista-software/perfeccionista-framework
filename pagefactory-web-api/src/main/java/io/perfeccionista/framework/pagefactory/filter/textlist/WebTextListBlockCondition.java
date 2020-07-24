package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.Condition;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebTextListBlockCondition extends Condition {

    WebTextListBlockCondition and(@NotNull WebTextListBlockCondition condition);

    WebTextListBlockCondition or(@NotNull WebTextListBlockCondition condition);

    WebTextListBlockCondition inverse();

    Deque<WebTextListBlockConditionHolder> getChildConditions();

    @NotNull WebFilterResult process(@NotNull WebTextList element, @Nullable String hash);

}
