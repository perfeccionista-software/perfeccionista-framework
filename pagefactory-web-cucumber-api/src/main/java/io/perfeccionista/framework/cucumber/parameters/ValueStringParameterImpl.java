package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.IncorrectStringValueResult;
import io.perfeccionista.framework.value.string.StringValue;
import io.perfeccionista.framework.value.string.StringValueResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.STRING_VALUE_RESULT_IS_NULL;

public class ValueStringParameterImpl implements ValueStringParameter {

    private final Environment environment;
    private final String rawInput;

    public ValueStringParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull String getNotNullProcessedValue() {
        return Optional.ofNullable(getProcessedValue())
                .orElseThrow(() -> IncorrectStringValueResult.exception(STRING_VALUE_RESULT_IS_NULL.getMessage()));
    }

    @Override
    public @Nullable String getProcessedValue() {
        return getValue().get();
    }

    @Override
    public @NotNull StringValue getValue() {
        return new StringValueResolver(environment, rawInput).getStringValue();
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
