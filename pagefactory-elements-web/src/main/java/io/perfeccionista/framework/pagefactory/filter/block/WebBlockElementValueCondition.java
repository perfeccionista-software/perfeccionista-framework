package io.perfeccionista.framework.pagefactory.filter.block;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebBlockElementValueCondition implements WebBlockCondition {

    private final String elementName;
    private final WebChildElement elementMock;

    private final StringValue stringValue;
    private final NumberValue<?> numberValue;

    public WebBlockElementValueCondition(String elementName, StringValue stringValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebBlockElementValueCondition(String elementName, NumberValue<?> numberValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebBlockElementValueCondition(WebChildElement elementMock, StringValue stringValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebBlockElementValueCondition(WebChildElement elementMock, NumberValue<?> numberValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    @Override
    public WebBlockCondition and(WebBlockCondition condition) {

        return this;
    }

    @Override
    public WebBlockCondition or(WebBlockCondition condition) {

        return this;
    }
}
