package io.perfeccionista.framework.bdd.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.number.NumberValue;

public class ValueRuIntegerParameter implements ValueIntegerParameter {

    private final Environment environment;
    private final String rawInput;

    public ValueRuIntegerParameter(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    // TODO: Тут нужно экстендить NumberValue<Integer> и оборачивать парсером условий для сравнения и трансформаций
    @Override
    public int getProcessedValue() {
        return environment.getService(ValueService.class).intProcess(rawInput);
    }

    // TODO: Тут нужно экстендить NumberValue<Integer> и оборачивать парсером условий для сравнения и трансформаций
    @Override
    public NumberValue<Integer> getValue() {
        return environment.getService(ValueService.class).intEquals(Integer.parseInt(rawInput));
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
