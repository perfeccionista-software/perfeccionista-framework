package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.pagefactory.elements.base.Element;

public interface WebBlockElementParameter<T extends Element> extends BddStepParameter {

    T find();

    <R extends Element> R find(Class<R> element);

}