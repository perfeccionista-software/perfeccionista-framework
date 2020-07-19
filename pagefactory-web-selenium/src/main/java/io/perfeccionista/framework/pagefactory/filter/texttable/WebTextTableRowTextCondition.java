package io.perfeccionista.framework.pagefactory.filter.texttable;

import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextBlockConditionHolder;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;

public class WebTextTableRowTextCondition implements WebTextTableRowCondition {

    private final String columnName;
    private final StringValue stringValue;
    private final NumberValue<?> numberValue;

    private boolean inverse = false;

    public WebTextTableRowTextCondition(String columnName, StringValue stringValue) {
        this.columnName = columnName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebTextTableRowTextCondition(String columnName, NumberValue<?> numberValue) {
        this.columnName = columnName;
        this.stringValue = null;
        this.numberValue = numberValue;
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
    public WebTextTableRowTextCondition inverse() {
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
