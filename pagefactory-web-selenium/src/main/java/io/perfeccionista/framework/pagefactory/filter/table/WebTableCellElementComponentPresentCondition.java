package io.perfeccionista.framework.pagefactory.filter.table;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;

public class WebTableCellElementComponentPresentCondition implements WebTableCellCondition {

    private final String columnName;
    private final String componentName;
    private final WebChildElement elementMock;
    private final String elementName;

    public WebTableCellElementComponentPresentCondition(String columnName, WebChildElement elementMock, String componentName) {
        this.columnName = columnName;
        this.elementName = null;
        this.elementMock = elementMock;
        this.componentName = componentName;
    }

    public WebTableCellElementComponentPresentCondition(String columnName, String elementName, String componentName) {
        this.columnName = columnName;
        this.elementName = elementName;
        this.elementMock = null;
        this.componentName = componentName;
    }

    public WebTableCellElementComponentPresentCondition inverse() {

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
