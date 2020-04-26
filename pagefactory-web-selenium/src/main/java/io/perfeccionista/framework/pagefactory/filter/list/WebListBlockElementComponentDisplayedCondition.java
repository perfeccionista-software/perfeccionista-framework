package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;

public class WebListBlockElementComponentDisplayedCondition implements WebListBlockCondition {

    private final String componentName;
    private final WebChildElement elementMock;
    private final String elementName;

    public WebListBlockElementComponentDisplayedCondition(WebChildElement elementMock, String componentName) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.componentName = componentName;
    }

    public WebListBlockElementComponentDisplayedCondition(String elementName, String componentName) {
        this.elementName = elementName;
        this.elementMock = null;
        this.componentName = componentName;
    }

    public WebListBlockElementComponentDisplayedCondition inverse() {
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
