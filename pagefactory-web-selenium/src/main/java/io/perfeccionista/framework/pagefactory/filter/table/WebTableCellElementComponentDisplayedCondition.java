package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

public class WebTableCellElementComponentDisplayedCondition implements WebTableCellCondition {

    private final String columnName;
    private final String componentName;
    private final WebChildElement elementMock;
    private final String elementName;

    public WebTableCellElementComponentDisplayedCondition(String columnName, WebChildElement elementMock, String componentName) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
        this.componentName = componentName;
    }

    public WebTableCellElementComponentDisplayedCondition(String columnName, String elementName, String componentName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
        this.componentName = componentName;
    }

    public WebTableCellElementComponentDisplayedCondition inverse() {

        return this;
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
