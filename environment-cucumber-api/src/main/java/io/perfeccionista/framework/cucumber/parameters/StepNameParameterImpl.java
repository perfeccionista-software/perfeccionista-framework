package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;

// TODO: Тут еще возможно необходимо сразу дописывать в аллюр создание шага
public class StepNameParameterImpl implements StepNameParameter {

    private final Environment environment;
    private final String rawInput;

    public StepNameParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
