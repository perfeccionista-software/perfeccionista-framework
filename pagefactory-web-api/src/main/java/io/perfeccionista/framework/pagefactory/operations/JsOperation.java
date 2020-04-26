package io.perfeccionista.framework.pagefactory.operations;

import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.JsFunction;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;

public class JsOperation<R> {

    /**
     *
     * @param locatorChain
     * @param jsFunction - экземпляр функции, содержащий аргумент вызова.
     * @param <I> Input type
     * @param <R> Result type
     * @return
     */
    public static <I, R> JsOperation<SingleResult<R>> single(LocatorChain locatorChain, JsFunction<I, R> jsFunction) {
        return new JsOperation<>();
    }

    public static <I, R> JsOperation<MultipleResult<R>> multiple(LocatorChain locatorChain, JsFunction<I, R> jsFunction) {
        return new JsOperation<>();
    }

}
