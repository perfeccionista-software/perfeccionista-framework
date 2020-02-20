package io.perfeccionista.framework.pagefactory.js;

public interface JsFunction<R, T> {

    Class<T> getResultType();

    T convert(R result);

    @Override
    String toString();

}
