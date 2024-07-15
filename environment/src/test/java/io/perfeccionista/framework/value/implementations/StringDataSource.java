package io.perfeccionista.framework.value.implementations;

import io.perfeccionista.framework.datasource.DataSource;
import io.perfeccionista.framework.exceptions.ValueNotFound;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.name.Name;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_VALUE_NOT_FOUND;

@Name("userName")
public class StringDataSource implements DataSource<String, String> {

    @Override
    public boolean contains(@NotNull String key) {
        return false;
    }

    @Override
    public String get(@NotNull String key) {
        return getOptional(key)
                .orElseThrow(() -> ValueNotFound.exception(DATA_SOURCE_VALUE_NOT_FOUND.getMessage(this.getClass().getCanonicalName(), key)));
    }

    @Override
    public Optional<String> getOptional(@NotNull String key) {
        return Optional.of(key + " Smith");
    }

}
