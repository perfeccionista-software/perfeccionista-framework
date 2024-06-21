package io.perfeccionista.framework.value.implementations;

import io.perfeccionista.framework.datasource.DataSource;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.name.Name;

import java.util.Optional;

@Name("userName")
public class StringDataSource implements DataSource<String, String> {

    @Override
    public boolean contains(@NotNull String key) {
        return false;
    }

    @Override
    public Optional<String> get(@NotNull String key) {
        return Optional.of(key + " Smith");
    }

}
