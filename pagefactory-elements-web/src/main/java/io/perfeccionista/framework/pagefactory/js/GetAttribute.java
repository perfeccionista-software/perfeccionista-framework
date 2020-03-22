package io.perfeccionista.framework.pagefactory.js;

public class GetAttribute implements JsFunction<String, String> {

    @Override
    public String convert(String json) {
        return json;
    }

    @Override
    public Class<String> getResultType() {
        return null;
    }

    public void setAttribute(String attributeName) {

    }

}

