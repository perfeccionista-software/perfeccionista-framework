package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableRowConditionHolder;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public class WebRadioButtonLabelCondition implements WebRadioButtonCondition {

    private final StringValue stringValue;
    private final NumberValue<? extends Number> numberValue;

    private boolean inverse = false;

    public WebRadioButtonLabelCondition(StringValue stringValue) {
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebRadioButtonLabelCondition(NumberValue<? extends Number> numberValue) {
        this.stringValue = null;
        this.numberValue = numberValue;
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
    public WebRadioButtonLabelCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebTableRowConditionHolder> getChildConditions() {
        return null;
    }

    @Override
    public @NotNull WebConditionProcessingResult process(@NotNull WebTable element, @Nullable String hash) {
        return null;
    }

}
