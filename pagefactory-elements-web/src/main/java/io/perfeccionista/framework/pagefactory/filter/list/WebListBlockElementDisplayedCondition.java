package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsDisplayedAvailable;

public class WebListBlockElementDisplayedCondition implements WebListBlockCondition {

    private final String elementName;
    private final IsDisplayedAvailable elementMock;

    public WebListBlockElementDisplayedCondition(IsDisplayedAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

     public WebListBlockElementDisplayedCondition(String elementName) {
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
