package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.string.StringValue;

public class ValueRuStringParameter implements ValueStringParameter {

    private final Environment environment;
    private final String rawInput;

    public ValueRuStringParameter(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    // TODO: Тут нужно экстендить StringValue и оборачивать парсером условий для сравнения и трансформаций
    @Override
    public String getProcessedValue() {
        return environment.getService(ValueService.class).stringProcess(rawInput);
    }

    // TODO: Тут нужно экстендить StringValue и оборачивать парсером условий для сравнения и трансформаций
    @Override
    public StringValue getValue() {
        return environment.getService(ValueService.class).stringEquals(rawInput);
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
