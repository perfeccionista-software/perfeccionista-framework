package io.perfeccionista.framework.pagefactory.elements.base;

import java.util.Optional;

public interface ParentElement<T extends ChildElement<?>> extends Element {

    /**
     * Возвращает элемент по указанному пути.
     * @param elementPath - последовательные имена Элементов, разделенные стрелками '->'
     * @return
     */
    Optional<T> getElementByPath(String elementPath);

    <R extends T> Optional<R> getElementByPath(String elementPath, Class<R> elementClass);

    void invokeMethod(String methodName, Object... args);

}
