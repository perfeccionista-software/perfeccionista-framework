package io.perfeccionista.framework.pagefactory.elements.locators;

public enum WebLocatorStrategy {

    SELF_NODE("selfNode"),
    ID("id"),
    CSS("css"),
    XPATH("xpath"),
    CLASS_NAME("className"),
    TAG_NAME("tagName"),
    NAME("name"),
    DTI("dti"),
    TEXT("text"),
    CONTAINS_TEXT("containsText");

    private final String strategyName;

    WebLocatorStrategy(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyName() {
        return strategyName;
    }

}
