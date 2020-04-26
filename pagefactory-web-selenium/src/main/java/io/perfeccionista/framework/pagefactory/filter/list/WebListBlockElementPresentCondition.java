package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;

public class WebListBlockElementPresentCondition implements WebListBlockCondition {

    private final String elementName;
    private final IsPresentAvailable elementMock;

    public WebListBlockElementPresentCondition(IsPresentAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementPresentCondition(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    public WebListBlockElementPresentCondition inverse() {
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
