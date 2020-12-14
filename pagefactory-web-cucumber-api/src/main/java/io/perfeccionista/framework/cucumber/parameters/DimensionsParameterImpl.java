package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.DimensionsFormatNotResolved;
import io.perfeccionista.framework.measurements.Dimensions;
import io.perfeccionista.framework.measurements.DimensionsCucumberResolver;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.DIMENSIONS_FORMAT_NOT_RESOLVED;

public class DimensionsParameterImpl implements DimensionsParameter {

    private final Environment environment;
    private final String rawInput;

    public DimensionsParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public Dimensions getDimensions() {
        return environment.getService(CucumberService.class)
                .resolveFirst(DimensionsCucumberResolver.class, rawInput)
                .orElseThrow(() -> DimensionsFormatNotResolved.exception(DIMENSIONS_FORMAT_NOT_RESOLVED.getMessage(rawInput)));
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
