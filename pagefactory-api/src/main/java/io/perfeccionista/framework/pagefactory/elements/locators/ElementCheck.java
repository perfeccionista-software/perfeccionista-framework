package io.perfeccionista.framework.pagefactory.elements.locators;

import io.perfeccionista.framework.pagefactory.js.JsFunction;

public interface ElementCheck extends JsFunction<Void, Void> {

    @Override
    default Class<Void> getResultType() {
        return Void.class;
    }

}
