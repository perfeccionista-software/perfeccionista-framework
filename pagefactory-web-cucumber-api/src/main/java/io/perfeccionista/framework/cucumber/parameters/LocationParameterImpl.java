package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.CucumberService;
import io.perfeccionista.framework.exceptions.LocationFormatNotResolved;
import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.measurements.LocationCucumberResolver;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebCucumberApiMessages.LOCATION_FORMAT_NOT_RESOLVED;

public class LocationParameterImpl implements LocationParameter {

    private final Environment environment;
    private final String rawInput;

    public LocationParameterImpl(Environment environment, String rawInput) {
        this.environment = environment;
        this.rawInput = rawInput;
    }

    @Override
    public @NotNull Location getLocation() {
        return environment.getService(CucumberService.class)
                .resolveFirst(LocationCucumberResolver.class, rawInput)
                .orElseThrow(() -> LocationFormatNotResolved.exception(LOCATION_FORMAT_NOT_RESOLVED.getMessage(rawInput)));
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
