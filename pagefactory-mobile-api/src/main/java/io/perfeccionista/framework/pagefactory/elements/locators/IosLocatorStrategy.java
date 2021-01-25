package io.perfeccionista.framework.pagefactory.elements.locators;

public enum IosLocatorStrategy implements LocatorStrategy {

    ACCESSIBILITY_ID("accessibilityId"),
    CLASS_NAME("className"),
    ID("id"),
    NAME("name"),
    XPATH("xpath"),
    IMAGE("image"),

    IOS_CLASS_CHAIN("iosClassChain"),
    IOS_NS_PREDICATE("iosNsPredicate");

    private final String strategyName;

    IosLocatorStrategy(String strategyName) {
        this.strategyName = strategyName;
    }

    @Override
    public String getStrategyName() {
        return strategyName;
    }

}
