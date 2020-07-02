package io.perfeccionista.framework.pagefactory.factory.proxy.mock;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

public class WebMappedBlockMock implements WebElementMock {

    protected WebElementRegistry elementRegistry;
    protected WebElementMock parentMock;
    protected Method parentMethod;
    protected Class<?> itemClass;

    public WebElementMock getParentMock() {
        return parentMock;
    }

    @Override
    public WebElementMock setParentMock(WebElementMock parentMock) {
        this.parentMock = parentMock;
        return this;
    }

    public Method getParentMethod() {
        return parentMethod;
    }

    @Override
    public WebElementMock setParentMethod(Method parentMethod) {
        this.parentMethod = parentMethod;
        return this;
    }

    public Class<?> getItemClass() {
        return itemClass;
    }

    @Override
    public WebElementMock setItemClass(Class<?> itemClass) {
        this.itemClass = itemClass;
        return this;
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChainTo(String locatorName) {
        return WebLocatorChain.empty();
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChain() {
        return WebLocatorChain.empty();
    }
}
