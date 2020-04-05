package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.IsSelectedAvailable;

public class WebTableCellElementSelectedCondition implements WebTableCellCondition {

    private final String columnName;
    private final String elementName;
    private final IsSelectedAvailable elementMock;

    public WebTableCellElementSelectedCondition(String columnName, IsSelectedAvailable elementMock) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebTableCellElementSelectedCondition(String columnName, String elementName) {
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
