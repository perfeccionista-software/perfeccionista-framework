package io.perfeccionista.framework.pagefactory.elements.base;

import io.perfeccionista.framework.pagefactory.elements.registry.ElementsRegistry;

import java.util.Optional;

public interface ParentElement extends Element {

    ElementsRegistry getElementRegistry();

    /**
     * Возвращает элемент по указанному пути.
     * @param elementPath - последовательные имена Элементов, разделенные стрелками '->'
     * @return
     */
    Optional<Element> getElementByPath(String elementPath);

    <T extends Element> Optional<T> getElementByPath(String elementPath, Class<T> elementClass);

    void invokeMethod(String methodName, Object... args);

}
