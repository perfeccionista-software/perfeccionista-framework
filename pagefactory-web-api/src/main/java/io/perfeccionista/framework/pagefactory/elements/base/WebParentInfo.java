package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;

public final class WebParentInfo {

    private final String parentHash;
    private final WebLocatorHolder webLocatorHolder;
    private final int index;

    private WebParentInfo(String parentHash, WebLocatorHolder webLocatorHolder, int index) {
        this.parentHash = parentHash;
        this.webLocatorHolder = webLocatorHolder;
        this.index = index;
    }

    public static WebParentInfo of(String parentHash, WebLocatorHolder webLocatorHolder, int index) {
        return new WebParentInfo(parentHash, webLocatorHolder, index);
    }

    public String getParentHash() {
        return parentHash;
    }

    public WebLocatorHolder getWebLocatorHolder() {
        return webLocatorHolder;
    }

    public int getIndex() {
        return index;
    }

}
