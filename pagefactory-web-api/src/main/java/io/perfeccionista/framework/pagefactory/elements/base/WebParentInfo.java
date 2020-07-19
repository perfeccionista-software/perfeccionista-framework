package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;

import java.util.Deque;

public final class WebParentInfo<T extends WebChildElement> {

    private final T parent;
    private final String parentHash;
    private final Deque<WebLocatorHolder> parentLocators;

    private WebParentInfo(T parent, String parentHash, Deque<WebLocatorHolder> parentLocators) {
        this.parent = parent;
        this.parentHash = parentHash;
        this.parentLocators = parentLocators;
    }

    public static <T extends WebChildElement> WebParentInfo<T> of(T parent, String parentHash, Deque<WebLocatorHolder> parentLocators) {
        return new WebParentInfo<>(parent, parentHash, parentLocators);
    }

    public T getParent() {
        return parent;
    }

    public String getParentHash() {
        return parentHash;
    }

    public Deque<WebLocatorHolder> getParentLocators() {
        return parentLocators;
    }

}
