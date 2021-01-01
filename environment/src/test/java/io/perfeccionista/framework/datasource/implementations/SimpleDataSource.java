package io.perfeccionista.framework.datasource.implementations;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.datasource.DataSource;
import io.perfeccionista.framework.datasource.entities.Professional;
import io.perfeccionista.framework.datasource.entities.User;

public class SimpleDataSource implements DataSource<String, User> {

    @Override
    public @Nullable User get(@NotNull String key) {
        if (key.startsWith("Professional")) {
            return new Professional(key, "White");
        }
        return new User(key, "Black");
    }

    @Override
    public <T extends User> @Nullable T get(@NotNull String key, @NotNull Class<T> clazz) {
        return (T) get(key);
    }

    @Override
    public @Nullable String getString(@NotNull String key) {
        return get(key).toString();
    }

}
