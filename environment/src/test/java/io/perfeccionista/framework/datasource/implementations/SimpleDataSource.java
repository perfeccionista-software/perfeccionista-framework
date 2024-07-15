package io.perfeccionista.framework.datasource.implementations;

import io.perfeccionista.framework.datasource.ObjectDataSource;
import io.perfeccionista.framework.exceptions.ValueNotFound;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.datasource.entities.Professional;
import io.perfeccionista.framework.datasource.entities.User;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_VALUE_NOT_FOUND;

public class SimpleDataSource implements ObjectDataSource<String, User> {

    @Override
    public User get(@NotNull String key) {
        return getOptional(key)
                .orElseThrow(() -> ValueNotFound.exception(DATA_SOURCE_VALUE_NOT_FOUND.getMessage(this.getClass().getCanonicalName(), key)));
    }

    @Override
    public Optional<User> getOptional(@NotNull String key) {
        if (key.startsWith("Professional")) {
            return Optional.of(new Professional(key, "White"));
        }
        return Optional.of(new User(key, "Black"));
    }

    @Override
    public <T extends User> T get(@NotNull String key, @NotNull Class<T> clazz) {
        return getOptional(key, clazz)
                .orElseThrow(() -> ValueNotFound.exception(DATA_SOURCE_VALUE_NOT_FOUND.getMessage(this.getClass().getCanonicalName(), key)));
    }

    @Override
    public <T extends User> Optional<T> getOptional(@NotNull String key, @NotNull Class<T> clazz) {
        return getOptional(key)
                .map(user -> (T) user);
    }

    @Override
    public String getString(@NotNull String key) {
        return getOptional(key)
                .map(User::toString)
                .orElseThrow(() -> ValueNotFound.exception(DATA_SOURCE_VALUE_NOT_FOUND.getMessage(this.getClass().getCanonicalName(), key)));
    }

    @Override
    public Optional<String> getOptionalString(@NotNull String key) {
        return getOptional(key)
                .map(User::toString);
    }

    @Override
    public boolean contains(@NotNull String key) {
        return false;
    }

}
