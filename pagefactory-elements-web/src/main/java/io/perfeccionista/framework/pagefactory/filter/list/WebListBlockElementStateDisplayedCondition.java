package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;

public class WebListBlockElementStateDisplayedCondition implements WebListBlockCondition {

    private final String stateName;
    private final WebChildElement elementMock;
    private final String elementName;

    public WebListBlockElementStateDisplayedCondition(WebChildElement elementMock, String stateName) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.stateName = stateName;
    }

    public WebListBlockElementStateDisplayedCondition(String elementName, String stateName) {
        this.elementName = elementName;
        this.elementMock = null;
        this.stateName = stateName;
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
