package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.ValueService;
import io.perfeccionista.framework.value.number.NumberValue;

public class ValueRuNumberParameter implements ValueNumberParameter {

    private final Environment environment;
    private final String rawInput;

    public ValueRuNumberParameter(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    // TODO: Тут нужно экстендить NumberValue<Integer> и оборачивать парсером условий для сравнения и трансформаций
    @Override
    public Number getProcessedValue() {
        return environment.getService(ValueService.class).intProcess(rawInput);
    }

    // TODO: Тут нужно экстендить NumberValue<Integer> и оборачивать парсером условий для сравнения и трансформаций
    @Override
    public NumberValue<Number> getValue() {
        return null;
        // TODO: Парсим еще и тип числа: Integer, Double, BigDecimal
        // Возможно, разбирать число придется на стороне параметра исходя из типа переданного числа
//        return environment.getService(ValueService.class).intEquals(Integer.parseInt(rawInput));
    }

    @Override
    public String getRaw() {
        return rawInput;
    }

}
