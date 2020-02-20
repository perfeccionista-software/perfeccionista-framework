package io.perfeccionista.framework.pagefactory.js;

public class IsDisplayed implements JsFunction<Boolean, Boolean> {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Boolean convert(Boolean result) {
        return result;
    }

    @Override
    public Class<Boolean> getResultType() {
        return null;
    }
}
