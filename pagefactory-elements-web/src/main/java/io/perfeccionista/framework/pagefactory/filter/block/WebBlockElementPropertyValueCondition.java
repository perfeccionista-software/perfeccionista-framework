package io.perfeccionista.framework.pagefactory.filter.block;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebBlockElementPropertyValueCondition implements WebBlockCondition {

    private final String elementName;
    private final WebChildElement elementMock;

    private final String propertyName;
    private final StringValue stringValue;
    private final NumberValue<?> numberValue;

    public WebBlockElementPropertyValueCondition(String elementName, String propertyName, StringValue stringValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.propertyName = propertyName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebBlockElementPropertyValueCondition(String elementName, String propertyName, NumberValue<?> numberValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.propertyName = propertyName;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebBlockElementPropertyValueCondition(WebChildElement elementMock, String propertyName, StringValue stringValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.propertyName = propertyName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebBlockElementPropertyValueCondition(WebChildElement elementMock, String propertyName, NumberValue<?> numberValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.propertyName = propertyName;
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
