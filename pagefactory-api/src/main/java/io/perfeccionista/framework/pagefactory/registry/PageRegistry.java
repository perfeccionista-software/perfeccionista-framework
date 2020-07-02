package io.perfeccionista.framework.pagefactory.registry;

import io.perfeccionista.framework.pagefactory.elements.base.Element;

@Deprecated
public interface PageRegistry<T extends Element> {

    T getByClass(Class<T> pageClass);

    T getByName(String pageName);

}
