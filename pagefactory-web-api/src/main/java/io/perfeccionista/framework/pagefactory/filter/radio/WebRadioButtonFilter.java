package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.WebFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebRadioButtonFilter extends WebFilter<WebRadioGroup, WebRadioButtonFilterResult> {

    WebRadioButtonFilter add(@NotNull WebRadioButtonCondition condition);

    WebRadioButtonFilter subtract(@NotNull WebRadioButtonCondition condition);

    @Override
    @NotNull WebRadioButtonFilterResult filter(@NotNull WebRadioGroup element);

    Deque<WebRadioButtonConditionHolder> getConditions();

}
