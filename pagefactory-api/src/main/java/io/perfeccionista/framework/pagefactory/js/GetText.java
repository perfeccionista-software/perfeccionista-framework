package io.perfeccionista.framework.pagefactory.js;

public class GetText implements JsFunction<String, String> {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String convert(String json) {
        return json;
    }

    @Override
    public Class<String> getResultType() {
        return null;
    }
}
