package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;

public class WebListBlockElementSelectedCondition implements WebListBlockCondition {

    private final IsSelectedAvailable elementMock;
    private final String elementName;

    public WebListBlockElementSelectedCondition(IsSelectedAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementSelectedCondition(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    public WebListBlockElementSelectedCondition inverse() {
        return this;
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

