package io.perfeccionista.framework.pagefactory.registry;

import io.perfeccionista.framework.pagefactory.elements.base.Element;

import java.util.Optional;

public interface PageRegistry<T extends Element> {

    Optional<T> getByClass(Class<? extends T> pageClass);

    Optional<T> getByName(String pageName);

}
