package io.perfeccionista.framework.value.implementations;

import io.perfeccionista.framework.datasource.ObjectDataSource;
import io.perfeccionista.framework.datasource.entities.User;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.name.Name;

import java.util.Optional;

@Name("user")
public class UserDataSource implements ObjectDataSource<String, User> {

    @Override
    public Optional<User> get(@NotNull String key) {
        return Optional.of(new User(key, "Smith"));
    }

    @Override
    public <T extends User> Optional<T> get(@NotNull String key, @NotNull Class<T> clazz) {
        return get(key).map(user -> (T) user);
    }

    @Override
    public Optional<String> getString(@NotNull String key) {
        return get(key).map(user -> user.toString());
    }

    @Override
    public boolean contains(@NotNull String key) {
        return false;
    }

}
