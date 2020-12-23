package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.DurationFormatNotResolved;
import io.perfeccionista.framework.measurements.DurationCucumberResolver;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentCucumberApiMessages.DURATION_FORMAT_NOT_RESOLVED;

public class DurationParameterImpl implements DurationParameter {

    private final Environment environment;
    private final String rawInput;

    public DurationParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull Duration getDuration() {
        return environment.getService(CucumberService.class)
                .resolveFirst(DurationCucumberResolver.class, rawInput)
                .orElseThrow(() -> DurationFormatNotResolved.exception(DURATION_FORMAT_NOT_RESOLVED.getMessage(rawInput)));
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
