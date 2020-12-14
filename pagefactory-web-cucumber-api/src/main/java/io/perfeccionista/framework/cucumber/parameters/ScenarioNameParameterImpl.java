package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;

public class ScenarioNameParameterImpl implements ScenarioNameParameter {

    private final Environment environment;
    private final String rawInput;

    public ScenarioNameParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
