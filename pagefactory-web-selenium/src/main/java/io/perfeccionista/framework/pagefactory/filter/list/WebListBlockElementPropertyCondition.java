package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

public class WebListBlockElementPropertyCondition implements WebListBlockCondition {

    private final WebChildElement elementMock;
    private final String elementName;

    private final String propertyName;
    private final StringValue stringValue;
    private final NumberValue<? extends Number> numberValue;

    public WebListBlockElementPropertyCondition(WebChildElement elementMock, String propertyName, StringValue stringValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.propertyName = propertyName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebListBlockElementPropertyCondition(WebChildElement elementMock, String propertyName, NumberValue<? extends Number> numberValue) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.propertyName = propertyName;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebListBlockElementPropertyCondition(String elementName, String propertyName, StringValue stringValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.propertyName = propertyName;
        this.stringValue = stringValue;
        this.numberValue = null;
    }

    public WebListBlockElementPropertyCondition(String elementName, String propertyName, NumberValue<? extends Number> numberValue) {
        this.elementName = elementName;
        this.elementMock = null;
        this.propertyName = propertyName;
        this.stringValue = null;
        this.numberValue = numberValue;
    }

    public WebListBlockElementPropertyCondition inverse() {
        return this;
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
