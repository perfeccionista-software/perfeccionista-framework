package io.perfeccionista.framework.pagefactory.factory.proxy.mock;

import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;

import java.lang.reflect.Method;

public interface WebElementMock extends WebLocatorChainAvailable {

    WebElementMock setParentMock(WebElementMock parentMock);

    WebElementMock getParentMock();

    WebElementMock setParentMethod(Method parentMethod);

    Method getParentMethod();

    WebElementMock setItemClass(Class<?> itemClass);

    Class<?> getItemClass();

}
