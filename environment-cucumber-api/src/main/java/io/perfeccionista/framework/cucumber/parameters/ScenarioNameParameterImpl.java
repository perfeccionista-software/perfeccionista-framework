package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;

// TODO: Тут еще возможно необходимо сразу дописывать логику поиска шага
public class ScenarioNameParameterImpl implements ScenarioNameParameter {

    private final Environment environment;
    private final String rawInput;

    public ScenarioNameParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
