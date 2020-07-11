package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;

// TODO: Необходимо доработать формат.
//  Тут логично указывать непосредственно объект родительского элемента:
//  WebChildElement: WebRadioGroup, WebList, WebTable, WebTextList, WebTextTable
public final class WebParentInfo {

    private final WebLocatorHolder webLocatorHolder; // radioButtonLocator, li, tr
    private final String parentHash;
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
