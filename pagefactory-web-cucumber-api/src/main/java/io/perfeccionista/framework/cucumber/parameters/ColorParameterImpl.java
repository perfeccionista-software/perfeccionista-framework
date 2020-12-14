package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.color.Color;
import io.perfeccionista.framework.color.WebColorCucumberResolver;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.WebColorFormatNotResolved;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.WEB_COLOR_FORMAT_NOT_RESOLVED;

public class ColorParameterImpl implements ColorParameter {

    private final Environment environment;
    private final String rawInput;

    public ColorParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public Color getColor() {
        return environment.getService(CucumberService.class)
                .resolveFirst(WebColorCucumberResolver.class, rawInput)
                .orElseThrow(() -> WebColorFormatNotResolved.exception(WEB_COLOR_FORMAT_NOT_RESOLVED.getMessage(rawInput)));
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
