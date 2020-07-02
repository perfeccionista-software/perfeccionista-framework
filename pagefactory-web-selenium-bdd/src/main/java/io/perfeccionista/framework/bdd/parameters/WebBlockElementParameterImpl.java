package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;

public class WebBlockElementParameterImpl<T extends WebChildElement> implements WebBlockElementParameter<T> {

    private final Environment environment;
    private final String rawInput;

    public WebBlockElementParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public T find() {
        return null;
    }

    @Override
    public <R extends WebChildElement> R find(Class<R> element) {
        return null;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
