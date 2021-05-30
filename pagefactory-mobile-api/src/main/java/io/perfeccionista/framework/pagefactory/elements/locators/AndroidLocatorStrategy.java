package io.perfeccionista.framework.pagefactory.elements.locators;

public enum AndroidLocatorStrategy implements LocatorStrategy {

    SELF_NODE("selfNode"),
    ID("id"),
    XPATH("xpath"),
    NAME("name"),
    TAG_NAME("tagName"),
    CLASS_NAME("className"),
    ACCESSIBILITY_ID("accessibilityId"),

    TEXT("text"),
    CONTAINS_TEXT("containsText"),

    ANDROID_VIEW_TAG("androidViewTag"),
    ANDROID_DATA_MATCHER("androidDataMatcher");

    private final String strategyName;

    AndroidLocatorStrategy(String strategyName) {
        this.strategyName = strategyName;
    }

    @Override
    public String getStrategyName() {
        return strategyName;
    }

}
