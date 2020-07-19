package io.perfeccionista.framework.pagefactory.filter.radio;

import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableRowConditionHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public class WebRadioButtonSelectedCondition implements WebRadioButtonCondition {

    private boolean inverse = false;

    @Override
    public WebRadioButtonCondition and(WebRadioButtonCondition condition) {
        return null;
    }

    @Override
    public WebRadioButtonCondition or(WebRadioButtonCondition condition) {
        return null;
    }

    @Override
    public WebRadioButtonSelectedCondition inverse() {
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
