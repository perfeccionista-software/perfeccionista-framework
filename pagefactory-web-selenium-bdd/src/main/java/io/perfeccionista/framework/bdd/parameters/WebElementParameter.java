package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.pagefactory.elements.base.Element;

import java.util.stream.Stream;

public interface WebElementParameter<T extends Element> extends BddStepParameter {

    T findSingle();

    <R extends Element> R findSingle(Class<R> elementClass);

    Stream<T> find();

    <R extends Element> Stream<R> find(Class<R> elementClass);

}
