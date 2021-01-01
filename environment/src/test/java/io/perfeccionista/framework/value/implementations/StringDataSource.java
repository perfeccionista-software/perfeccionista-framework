package io.perfeccionista.framework.value.implementations;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.datasource.DataSource;
import io.perfeccionista.framework.name.Name;

@Name("userName")
public class StringDataSource implements DataSource<String, String> {

    @Override
    public @Nullable String get(@NotNull String key) {
        return key + " Smith";
    }

    @Override
    public <T extends String> @Nullable T get(@NotNull String key, @NotNull Class<T> clazz) {
        return (T) get(key);
    }

    @Override
    public @Nullable String getString(@NotNull String key) {
        return get(key);
    }

}
