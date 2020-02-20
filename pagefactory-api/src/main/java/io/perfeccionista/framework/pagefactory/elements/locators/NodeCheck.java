package io.perfeccionista.framework.pagefactory.elements.locators;

import io.perfeccionista.framework.pagefactory.js.JsFunction;

public interface NodeCheck extends JsFunction<Void> {

    @Override
    default Class<Void> getResultType() {
        return Void.class;
    }

}
