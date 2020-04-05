package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsEnabledAvailable;

public class WebTableCellElementEnabledCondition implements WebTableCellCondition {

    private final String columnName;
    private final String elementName;
    private final IsEnabledAvailable elementMock;

    public WebTableCellElementEnabledCondition(String columnName, IsEnabledAvailable elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementEnabledCondition(String columnName, String elementName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
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
