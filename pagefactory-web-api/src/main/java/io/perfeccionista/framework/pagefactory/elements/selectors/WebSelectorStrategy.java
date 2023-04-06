package io.perfeccionista.framework.pagefactory.elements.selectors;

public enum WebSelectorStrategy {

    SELF_NODE("selfNode"),

    ID("id"),
    CSS("css"),
    XPATH("xpath"),
    CLASS_NAME("className"),
    TAG_NAME("tagName"),
    NAME("name"),
    DTI("dti"),
    EQUALS_TEXT("text"),
    CONTAINS_TEXT("containsText");

    private final String strategyName;

    WebSelectorStrategy(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyName() {
        return strategyName;
    }

}
