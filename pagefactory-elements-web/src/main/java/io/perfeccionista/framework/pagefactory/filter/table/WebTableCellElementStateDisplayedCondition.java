package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;

public class WebTableCellElementStateDisplayedCondition implements WebTableCellCondition {

    private final String columnName;
    private final String stateName;
    private final WebChildElement elementMock;
    private final String elementName;

    public WebTableCellElementStateDisplayedCondition(String columnName, WebChildElement elementMock, String stateName) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
        this.stateName = stateName;
    }

    public WebTableCellElementStateDisplayedCondition(String columnName, String elementName, String stateName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
        this.stateName = stateName;
    }

    @Override
    public WebTableCellCondition and(WebTableCellCondition condition) {

        return this;
    }

    @Override
    public WebTableCellCondition or(WebTableCellCondition condition) {

        return this;
    }

}
