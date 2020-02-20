package io.perfeccionista.framework.value.implementations;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.datasource.DataSource;
import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.value.implementations.entities.User;

@Name("user")
public class UserDataSource implements DataSource<String, User> {

    @Override
    public @Nullable User get(@NotNull String key) {
        return new User(key, "Smith");
    }

    @Override
    public <T extends User> @Nullable T get(@NotNull String key, @NotNull Class<T> clazz) {
        return (T) get(key);
    }

    @Override
    public @Nullable String getString(@NotNull String key) {
        User result = get(key);
        if (null != result) {
            return result.toString();
        }
        return null;
    }

}
