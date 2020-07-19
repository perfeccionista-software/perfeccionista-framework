package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextBlockConditionHolder;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public class WebTextTableRowIndexCondition implements WebTextTableRowCondition {

    private final NumberValue<Integer> value;

    private boolean inverse = false;

    public WebTextTableRowIndexCondition(NumberValue<Integer> value) {
        this.value = value;
    }

    @Override
    public WebTextTableRowCondition and(WebTextTableRowCondition condition) {
        return null;
    }

    @Override
    public WebTextTableRowCondition or(WebTextTableRowCondition condition) {
        return null;
    }

    @Override
    public WebTextTableRowIndexCondition inverse() {
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
