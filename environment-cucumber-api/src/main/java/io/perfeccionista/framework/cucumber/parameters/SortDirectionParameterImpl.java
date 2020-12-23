package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.comparators.SortDirection;
import io.perfeccionista.framework.comparators.SortDirectionCucumberResolver;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.SortDirectionFormatNotResolved;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentCucumberApiMessages.SORT_DIRECTION_FORMAT_NOT_RESOLVED;

public class SortDirectionParameterImpl implements SortDirectionParameter {

    private final Environment environment;
    private final String rawInput;

    public SortDirectionParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull SortDirection getDirection() {
        return environment.getService(CucumberService.class)
                .resolveFirst(SortDirectionCucumberResolver.class, rawInput)
                .orElseThrow(() -> SortDirectionFormatNotResolved.exception(SORT_DIRECTION_FORMAT_NOT_RESOLVED.getMessage(rawInput)));
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
