package io.perfeccionista.framework.value.implementations;

import io.perfeccionista.framework.datasource.ObjectDataSource;
import io.perfeccionista.framework.datasource.entities.User;
import io.perfeccionista.framework.exceptions.ValueNotFound;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.name.Name;

import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_VALUE_NOT_FOUND;

@Name("user")
public class UserDataSource implements ObjectDataSource<String, User> {

    @Override
    public User get(@NotNull String key) {
        return Optional.of(new User(key, "Smith"))
                .orElseThrow(() -> ValueNotFound.exception(DATA_SOURCE_VALUE_NOT_FOUND.getMessage(this.getClass().getCanonicalName(), key)));
    }

    @Override
    public Optional<User> getOptional(@NotNull String key) {
        return Optional.of(new User(key, "Smith"));
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
        return getOptionalString(key)
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
