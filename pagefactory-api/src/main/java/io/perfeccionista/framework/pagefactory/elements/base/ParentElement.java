package io.perfeccionista.framework.pagefactory.elements.base;

public interface ParentElement<T extends ChildElement<?>> extends Element {

    /**
     * Возвращает элемент по указанному пути.
     * @param elementPath - последовательные имена Элементов, разделенные стрелками '->'
     * @return
     */
    T getElementByPath(String elementPath);

    <R extends T> R getElementByPath(String elementPath, Class<R> elementClass);

}
