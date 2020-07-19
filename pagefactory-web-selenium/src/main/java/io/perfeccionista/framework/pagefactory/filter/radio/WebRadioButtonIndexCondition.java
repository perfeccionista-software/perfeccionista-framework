package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableRowConditionHolder;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public class WebRadioButtonIndexCondition implements WebRadioButtonCondition {

    private final NumberValue<Integer> value;

    private boolean inverse = false;

    public WebRadioButtonIndexCondition(NumberValue<Integer> value) {
        this.value = value;
    }

    @Override
    public WebRadioButtonCondition and(WebRadioButtonCondition condition) {
        return null;
    }

    @Override
    public WebRadioButtonCondition or(WebRadioButtonCondition condition) {
        return null;
    }

    @Override
    public WebRadioButtonIndexCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebTableRowConditionHolder> getChildConditions() {
        return null;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebTable element, @Nullable String hash) {
        return null;
    }

}
