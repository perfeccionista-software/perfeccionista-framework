package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.GetLabelAvailable;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebListBlockElementLabelCondition implements WebListBlockCondition {

    private final GetLabelAvailable elementMock;
    private final String elementName;

    private final StringValue stringValue;
    private final NumberValue<?> numberValue;

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
}
