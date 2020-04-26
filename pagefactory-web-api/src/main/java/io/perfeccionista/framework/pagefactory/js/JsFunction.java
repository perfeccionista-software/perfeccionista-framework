package io.perfeccionista.framework.pagefactory.js;

public interface JsFunction<I, R> {

    Class<R> getResultType();

    R convert(I result);

    @Override
    String toString();

}
