package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;

public class WebTableCellElementDisplayedCondition implements WebTableCellCondition {

    private final String columnName;
    private final String elementName;
    private final IsDisplayedAvailable elementMock;

    public WebTableCellElementDisplayedCondition(String columnName, IsDisplayedAvailable elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementDisplayedCondition(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
    }

    public WebTableCellElementDisplayedCondition inverse() {

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
