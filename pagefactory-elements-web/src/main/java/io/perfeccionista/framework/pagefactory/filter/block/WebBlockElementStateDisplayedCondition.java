package io.perfeccionista.framework.pagefactory.filter.block;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;

public class WebBlockElementStateDisplayedCondition implements WebBlockCondition {

    private final String stateName;
    private final String elementName;
    private final WebChildElement elementMock;

    public WebBlockElementStateDisplayedCondition(String elementName, String stateName) {
        this.elementName = elementName;
        this.elementMock = null;
        this.stateName = stateName;
    }

    public WebBlockElementStateDisplayedCondition(WebChildElement elementMock, String stateName) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.stateName = stateName;
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
