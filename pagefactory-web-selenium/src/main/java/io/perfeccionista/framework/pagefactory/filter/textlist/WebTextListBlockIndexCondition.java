package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public class WebTextListBlockIndexCondition implements WebTextListBlockCondition {

    private final NumberValue<Integer> value;

    private boolean inverse = false;

    public WebTextListBlockIndexCondition(NumberValue<Integer> value) {
        this.value = value;
    }

    @Override
    public WebTextListBlockIndexCondition and(WebTextListBlockCondition condition) {
        return null;
    }

    @Override
    public WebTextListBlockIndexCondition or(WebTextListBlockCondition condition) {
        return null;
    }

    @Override
    public WebTextListBlockIndexCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebTextBlockConditionHolder> getChildConditions() {
        return null;
    }

    @Override
    public @NotNull WebConditionProcessingResult process(@NotNull WebTextList element, @Nullable String hash) {
        return null;
    }
}
