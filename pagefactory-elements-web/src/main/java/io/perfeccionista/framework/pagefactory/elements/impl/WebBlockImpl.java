package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;

import java.util.Optional;

public abstract class WebBlockImpl extends AbstractWebChildElement implements WebBlock {

    protected WebElementRegistry elementsRegistry;

    @Override
    public WebElementRegistry getElementRegistry() {
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
