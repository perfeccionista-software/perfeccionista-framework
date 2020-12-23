package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.value.number.BigDecimalValueResolver;
import io.perfeccionista.framework.value.number.IntegerValueResolver;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;

public class ValueNumberParameterImpl implements ValueNumberParameter {

    private final Environment environment;
    private final String rawInput;

    public ValueNumberParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull NumberValue<? extends Number> getValue() {
        if (rawInput.contains(".")) {
            return new BigDecimalValueResolver(environment, rawInput).getBigDecimalValue();
        }
        return new IntegerValueResolver(environment, rawInput).getIntegerValue();
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
