package io.perfeccionista.framework.pagefactory.operations;

import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.SingleResult;
import io.perfeccionista.framework.pagefactory.js.JsFunction;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;

public class JsOperation<R> {

    /**
     *
     * @param locatorChain
     * @param jsFunctionClass
     * @param <I> Input type
     * @param <R> Result type
     * @return
     */
    public static <I, R> JsOperation<SingleResult<R>> single(LocatorChain locatorChain, Class<? extends JsFunction<I, R>> jsFunctionClass) {
        return new JsOperation<>();
    }

    public static <I, R> JsOperation<MultipleResult<R>> multiple(LocatorChain locatorChain, Class<? extends JsFunction<I, R>> jsFunctionClass) {
        return new JsOperation<>();
    }

}
