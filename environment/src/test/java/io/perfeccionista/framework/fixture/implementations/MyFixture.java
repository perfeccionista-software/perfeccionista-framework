package io.perfeccionista.framework.fixture.implementations;

import io.perfeccionista.framework.fixture.Fixture;
import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@Name("MyFixture")
public class MyFixture implements Fixture<String> {
    @Override
    public @NotNull Optional<String> execute() {
        return Optional.of("MyResult");
    }

    @Override
    public boolean revert() {
        return false;
    }
}
