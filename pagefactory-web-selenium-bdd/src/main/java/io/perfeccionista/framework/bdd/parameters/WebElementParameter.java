package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

import java.util.stream.Stream;

public interface WebElementParameter<T> extends BddStepParameter {

    T findSingle();

    <R extends WebChildElement> R findSingle(Class<R> elementClass);

    Stream<T> find();

    <R extends WebChildElement> Stream<R> find(Class<R> elementClass);

}
