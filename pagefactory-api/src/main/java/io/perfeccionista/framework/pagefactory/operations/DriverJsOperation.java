package io.perfeccionista.framework.pagefactory.operations;

import io.perfeccionista.framework.pagefactory.js.JsFunction;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;

public class DriverJsOperation<T> {


    public static <T> DriverJsOperation<T> of(LocatorChain locatorChain, Class<? extends JsFunction<T>> jsFunctionClass) {
        return new DriverJsOperation<>();
    }

}
