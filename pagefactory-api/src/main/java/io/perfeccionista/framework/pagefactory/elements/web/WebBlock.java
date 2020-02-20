package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.elements.Block;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.pagefactory.elements.registry.ElementsRegistry;

import java.util.Optional;

public class WebBlock extends AbstractWebChildElement implements Block {

    protected int index = -1;

    protected ElementsRegistry elementsRegistry;

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public ElementsRegistry getElementRegistry() {
        return elementsRegistry;
    }

    @Override
    public Optional<Element> getElementByPath(String elementPath) {
        return elementsRegistry.getElementByPath(elementPath);
    }

    @Override
    public <T extends Element> Optional<T> getElementByPath(String elementPath, Class<T> elementClass) {
        return elementsRegistry.getElementByPath(elementPath, elementClass);
    }

    @Override
    public void invokeMethod(String methodPath, Object... args) {
        elementsRegistry.invokeMethodByName(methodPath, this, args);
    }

}
