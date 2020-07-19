package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.GetLabelAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebListBlockElementLabelCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final GetLabelAvailable elementMock;
    private final String elementName;

    private final StringValue stringValue;
    private final NumberValue<?> numberValue;

    private boolean inverse = false;

    public WebListBlockElementLabelCondition(GetLabelAvailable elementMock, StringValue stringValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebListBlockElementLabelCondition(GetLabelAvailable elementMock, NumberValue<?> numberValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebListBlockElementLabelCondition(String elementName, StringValue stringValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebListBlockElementLabelCondition(String elementName, NumberValue<?> numberValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    @Override
    public WebListBlockCondition and(WebListBlockCondition condition) {

        return this;
    }

    @Override
    public WebListBlockCondition or(WebListBlockCondition condition) {

        return this;
    }

    @Override
    public WebListBlockElementLabelCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public WebConditionProcessingResult process(@NotNull WebList element, @Nullable String hash) {
        // TODO: Implement
        return null;
    }
}
