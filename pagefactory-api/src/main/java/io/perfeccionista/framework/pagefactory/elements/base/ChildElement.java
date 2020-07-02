package io.perfeccionista.framework.pagefactory.elements.base;


@Deprecated
public interface ChildElement<T extends ParentElement<?>> extends Element {

    T getParent();

    boolean isRequired();

}
