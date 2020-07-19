package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebRadioGroup;
import io.perfeccionista.framework.pagefactory.filter.WebFilterBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

public interface WebRadioButtonFilterBuilder extends WebFilterBuilder<WebRadioGroup, WebRadioButtonFilter> {

    @Override
    @NotNull WebRadioButtonFilter build(@NotNull WebRadioGroup element);

    WebRadioButtonFilterBuilder add(@NotNull WebRadioButtonCondition condition);

    WebRadioButtonFilterBuilder subtract(@NotNull WebRadioButtonCondition condition);

    Deque<WebRadioButtonConditionHolder> getConditions();

}
