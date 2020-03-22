package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.pagefactory.elements.AbstractChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import java.util.Map;

public interface ElementsConfiguration {

    <T extends Element, I extends AbstractChildElement<?>> Map<Class<T>, Class<I>> getElementImplementations();

}
