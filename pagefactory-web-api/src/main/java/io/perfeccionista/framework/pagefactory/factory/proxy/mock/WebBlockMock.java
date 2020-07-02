package io.perfeccionista.framework.pagefactory.factory.proxy.mock;

import io.perfeccionista.framework.name.WebElementIdentifier;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorRegistry;
import io.perfeccionista.framework.pagefactory.elements.registry.WebElementRegistry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.ROOT;

public class WebBlockMock implements WebElementMock {

    protected WebLocatorRegistry locatorRegistry;
    protected WebElementRegistry elementRegistry;
    protected WebElementIdentifier elementIdentifier;
    protected WebElementMock parentMock;
    protected Method parentMethod;
    protected Class<?> itemClass;

    @Override
    public WebElementMock getParentMock() {
        return parentMock;
    }

    @Override
    public WebElementMock setParentMock(WebElementMock parentMock) {
        this.parentMock = parentMock;
        return this;
    }

    @Override
    public Method getParentMethod() {
        return parentMethod;
    }

    @Override
    public WebElementMock setParentMethod(Method parentMethod) {
        this.parentMethod = parentMethod;
        return this;
    }

    @Override
    public Class<?> getItemClass() {
        return itemClass;
    }

    @Override
    public WebElementMock setItemClass(Class<?> itemClass) {
        this.itemClass = itemClass;
        return this;
    }

    public WebElementIdentifier getElementIdentifier() {
        return elementIdentifier;
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChainTo(String locatorName) {
        WebLocatorHolder locator = locatorRegistry.getLocator(locatorName);
        if (locator == null) {
            return getLocatorChain();
        }
        return getLocatorChain().addLocator(locator);
    }

    @Override
    public @NotNull WebLocatorChain getLocatorChain() {
        return parentMock.getLocatorChain().addLocator(locatorRegistry.getLocator(ROOT));
    }
}
