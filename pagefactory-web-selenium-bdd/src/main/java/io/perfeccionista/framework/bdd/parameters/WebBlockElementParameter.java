package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

public interface WebBlockElementParameter<T extends WebChildElement> extends BddStepParameter {

    T find();

    <R extends WebChildElement> R find(Class<R> element);

}