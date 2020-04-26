package io.perfeccionista.framework.bdd.parameters;

public class WebTableColumnParameterImpl implements WebTableColumnParameter {

    private final String rawInput;

    public WebTableColumnParameterImpl(String rawInput) {
        this.rawInput = rawInput;
    }

    @Override
    public String getRaw() {
        return null;
    }

}
