package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.methods.IsPresentAvailable;

public class WebTableCellElementPresentCondition implements WebTableCellCondition {

    private final String columnName;
    private final String elementName;
    private final IsPresentAvailable elementMock;

    public WebTableCellElementPresentCondition(String columnName, IsPresentAvailable elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementPresentCondition(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    public WebTableCellElementPresentCondition inverse() {

        return this;
    }

    @Override
    public WebTableCellCondition and(WebTableCellCondition condition) {
        return null;
    }

    @Override
    public WebTableCellCondition or(WebTableCellCondition condition) {
        return null;
    }

}

