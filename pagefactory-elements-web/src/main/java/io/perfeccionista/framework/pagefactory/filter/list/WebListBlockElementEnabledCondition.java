package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsEnabledAvailable;

public class WebListBlockElementEnabledCondition implements WebListBlockCondition {

    private final IsEnabledAvailable elementMock;
    private final String elementName;

    public WebListBlockElementEnabledCondition(IsEnabledAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementEnabledCondition(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebListBlockCondition and(WebListBlockCondition condition) {
        return null;
    }

    @Override
    public WebListBlockCondition or(WebListBlockCondition condition) {
        return null;
    }
}
