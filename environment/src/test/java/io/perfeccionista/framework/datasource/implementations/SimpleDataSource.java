package io.perfeccionista.framework.datasource.implementations;

import io.perfeccionista.framework.datasource.ObjectDataSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.datasource.entities.Professional;
import io.perfeccionista.framework.datasource.entities.User;

import java.util.Optional;

public class SimpleDataSource implements ObjectDataSource<String, User> {

    @Override
    public @Nullable Optional<User> get(@NotNull String key) {
        if (key.startsWith("Professional")) {
            return Optional.of(new Professional(key, "White"));
        }
        return Optional.of(new User(key, "Black"));
    }

    @Override
    public <T extends User> Optional<T> get(@NotNull String key, @NotNull Class<T> clazz) {
        return get(key).map(user -> (T) user);
    }

    @Override
    public @Nullable Optional<String> getString(@NotNull String key) {
        return get(key).map(User::toString);
    }

    @Override
    public boolean contains(@NotNull String key) {
        return false;
    }

}
