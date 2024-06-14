package io.perfeccionista.framework.datasource.implementations;

import io.perfeccionista.framework.datasource.ObjectDataStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import io.perfeccionista.framework.datasource.DataStorage;
import io.perfeccionista.framework.datasource.entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public @Nullable Optional<User> get(@NotNull String key) {
        return Optional.of(users.get(key));
    }

    @Override
    public <T extends User> Optional<T> get(@NotNull String key, @NotNull Class<T> clazz) {
        return get(key).map(user -> (T) user);
    }

    @Override
    public @Nullable Optional<String> getString(@NotNull String key) {
        return get(key).map(user -> user.toString());
    }

}
