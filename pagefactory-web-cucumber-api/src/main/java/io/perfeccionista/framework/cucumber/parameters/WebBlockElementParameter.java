package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

public interface WebBlockElementParameter<T extends WebChildElement> extends CucumberStepParameter {

    T find();

    <R extends WebChildElement> R find(Class<R> element);

}