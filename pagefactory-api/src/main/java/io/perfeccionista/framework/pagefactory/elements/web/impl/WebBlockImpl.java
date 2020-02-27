package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import io.perfeccionista.framework.pagefactory.elements.web.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.web.WebChildElement;

import java.util.Optional;

public class WebBlockImpl extends AbstractWebChildElement implements WebBlock {

    protected int index = -1;

    protected WebElementRegistry elementsRegistry;

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public WebElementRegistry getWebElementRegistry() {
        return this.elementsRegistry;
    }

    @Override
    public Optional<WebChildElement> getElementByPath(String elementPath) {
        return elementsRegistry.getElementByPath(elementPath);
    }

    @Override
    public <T extends WebChildElement> Optional<T> getElementByPath(String elementPath, Class<T> elementClass) {
        return elementsRegistry.getElementByPath(elementPath, elementClass);
    }

    @Override
    public void invokeMethod(String methodPath, Object... args) {
        elementsRegistry.invokeMethodByName(methodPath, this, args);
    }

}
