package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectIntegerValueResult;
import io.perfeccionista.framework.value.number.IntegerValueResolver;
import io.perfeccionista.framework.value.number.NumberValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentCucumberApiMessages.INTEGER_VALUE_RESULT_IS_NULL;

public class ValueIntegerParameterImpl implements ValueIntegerParameter {

    private final Environment environment;
    private final String rawInput;

    public ValueIntegerParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public int getNotNullProcessedValue() {
        return Optional.ofNullable(getProcessedValue())
                .orElseThrow(() -> IncorrectIntegerValueResult.exception(INTEGER_VALUE_RESULT_IS_NULL.getMessage()));
    }

    @Override
    public @Nullable Integer getProcessedValue() {
        return getValue().get();
    }

    @Override
    public @NotNull NumberValue<Integer> getValue() {
        return new IntegerValueResolver(environment, rawInput).getIntegerValue();
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
