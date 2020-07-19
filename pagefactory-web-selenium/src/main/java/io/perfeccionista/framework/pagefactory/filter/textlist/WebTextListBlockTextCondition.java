package io.perfeccionista.framework.pagefactory.filter.textlist;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public class WebTextListBlockTextCondition implements WebTextListBlockCondition {

    private final StringValue stringValue;
    private final NumberValue<? extends Number> numberValue;

    private boolean inverse = false;

    public WebTextListBlockTextCondition(StringValue stringValue) {
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebTextListBlockTextCondition(NumberValue<? extends Number> numberValue) {
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    @Override
    public WebTextListBlockTextCondition and(WebTextListBlockCondition condition) {
        return null;
    }

    @Override
    public WebTextListBlockTextCondition or(WebTextListBlockCondition condition) {
        return null;
    }

    @Override
    public WebTextListBlockTextCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebTextBlockConditionHolder> getChildConditions() {
        return null;
    }

    @Override
    public @NotNull WebFilterResult process(@NotNull WebTextList element, @Nullable String hash) {
        return null;
    }
}
