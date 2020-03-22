package io.perfeccionista.framework.pagefactory.filter.block;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;

public class WebBlockElementDisplayedCondition implements WebBlockCondition {

    private final String elementName;
    private final WebChildElement elementMock;

     public WebBlockElementDisplayedCondition(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    public WebBlockElementDisplayedCondition(WebChildElement elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    @Override
    public WebBlockCondition and(WebBlockCondition condition) {
        return null;
    }

    @Override
    public WebBlockCondition or(WebBlockCondition condition) {
        return null;
    }
}
