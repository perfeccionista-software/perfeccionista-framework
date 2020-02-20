package io.perfeccionista.framework.datasource.implementations;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.datasource.DataStorage;
import io.perfeccionista.framework.datasource.implementations.entities.User;

import java.util.Map;

public class SimpleDataStorage implements DataStorage<String, User> {

    private Map<String, User> users;

    public SimpleDataStorage(Map<String, User> users) {
        this.users = users;
    }

    @Override
    public void put(@NotNull String key, @Nullable User value) {
        users.put(key, value);
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
        return users.get(key);
    }

    @Override
    public <T extends User> @Nullable T get(@NotNull String key, @NotNull Class<T> clazz) {
        return (T) get(key);
    }

    @Override
    public @Nullable String getString(@NotNull String key) {
        return users.get(key).toString();
    }

}
