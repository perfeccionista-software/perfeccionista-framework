package io.perfeccionista.framework.datasource.implementations;

import io.perfeccionista.framework.datasource.ObjectDataStorage;
import io.perfeccionista.framework.exceptions.ValueNotFound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.datasource.DataStorage;
import io.perfeccionista.framework.datasource.entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_VALUE_NOT_FOUND;

public class SimpleDataStorage implements ObjectDataStorage<String, User> {

    private Map<String, User> users;

    public SimpleDataStorage() {
        Map<String, User> users = new HashMap<>();
        users.put("Jack", new User("Jack", "Black"));
        this.users = users;
    }

    public SimpleDataStorage(Map<String, User> users) {
        this.users = users;
    }

    @Override
    public DataStorage<String, User> put(@NotNull String key, @Nullable User value) {
        users.put(key, value);
        return this;
    }

    @Override
    public boolean putIfAbsent(@NotNull String key, @Nullable User value) {
        if (users.containsKey(key)) {
            return false;
        }
        users.put(key, value);
        return true;
    }

    @Override
    public boolean contains(@NotNull String key) {
        return users.containsKey(key);
    }

    @Override
    public @Nullable User get(@NotNull String key) {
        return getOptional(key)
                .orElseThrow(() -> ValueNotFound.exception(DATA_SOURCE_VALUE_NOT_FOUND.getMessage(this.getClass().getCanonicalName(), key)));
    }

    public Optional<User> getOptional(@NotNull String key) {
        return Optional.of(users.get(key));
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

}
