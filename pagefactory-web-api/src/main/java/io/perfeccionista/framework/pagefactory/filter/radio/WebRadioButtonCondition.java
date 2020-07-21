package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.Condition;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public interface WebRadioButtonCondition extends Condition {

    WebRadioButtonCondition and(@NotNull WebRadioButtonCondition condition);

    WebRadioButtonCondition or(@NotNull WebRadioButtonCondition condition);

    WebRadioButtonCondition inverse();

    Deque<WebRadioButtonConditionHolder> getChildConditions();

    @NotNull WebFilterResult process(@NotNull WebRadioGroup element, @Nullable String hash);

}
