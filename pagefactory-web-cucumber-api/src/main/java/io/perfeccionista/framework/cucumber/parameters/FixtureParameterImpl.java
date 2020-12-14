package io.perfeccionista.framework.cucumber.parameters;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.cucumber.parameters.datatable.entries.FixtureEntry;
import io.perfeccionista.framework.fixture.Fixture;
import io.perfeccionista.framework.fixture.FixtureParameters;
import io.perfeccionista.framework.fixture.FixtureService;
import io.perfeccionista.framework.fixture.ParametrizedFixture;
import io.perfeccionista.framework.value.ValueService;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class FixtureParameterImpl<S, T> implements FixtureParameter<S, T> {

    private final Environment environment;
    private final String rawInput;

    public FixtureParameterImpl(Environment environment, String fixtureName) {
        this.environment = environment;
        this.rawInput = fixtureName;
    }

    @Override
    public @NotNull Fixture<S, T> getFixtureInstance() {
        return environment.getService(FixtureService.class)
                .getFixture(rawInput);
    }

    @Override
    public @NotNull ParametrizedFixture<S, T> getFixtureInstance(@NotNull List<FixtureEntry> fixtureParameterEntries) {
        ValueService valueService = environment.getService(ValueService.class);
        // TODO: Возможно тут стоит сделать проверку на дублирование имен параметров
        Map<String, Object> fixtureParameters = fixtureParameterEntries.stream()
                .collect(toMap(FixtureEntry::getParameterName, fixtureEntry -> valueService.objectProcess(fixtureEntry.getParameterValue())));
        return environment.getService(FixtureService.class)
                .getParametrizedFixture(rawInput, FixtureParameters.of(fixtureParameters));
    }

    @Override
    public @NotNull String getRaw() {
        return rawInput;
    }

}
