package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.color.ColorCucumberResolver;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.ColorFormatNotResolved;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentCucumberApiMessages.COLOR_FORMAT_NOT_RESOLVED;

public class ColorParameterImpl implements ColorParameter {

    private final Environment environment;
    private final String rawInput;

    public ColorParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull Color getColor() {
        return environment.getService(CucumberService.class)
                .resolveFirst(ColorCucumberResolver.class, rawInput)
                .orElseThrow(() -> ColorFormatNotResolved.exception(COLOR_FORMAT_NOT_RESOLVED.getMessage(rawInput)));
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
