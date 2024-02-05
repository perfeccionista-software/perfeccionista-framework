package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Name("stash")
public final class StashDataSource implements DataStorage<String, Object> {

    private final Map<String, Object> storage = new HashMap<>();

    @Override
    public @Nullable Object get(@NotNull String key) {
        return storage.get(key);
    }

    @Override
    public <T> @Nullable T get(@NotNull String key, @NotNull Class<T> clazz) {
        return (T) get(key);
    }

    @Override
    public @Nullable String getString(@NotNull String key) {
        Object result = get(key);
        return result == null ? "" : result.toString();
    }

    @Override
    public StashDataSource put(@NotNull String key, @Nullable Object value) {
        storage.put(key, value);
        return this;
    }

    @Override
    public boolean putIfAbsent(@NotNull String key, @Nullable Object value) {
        Object updatedValue = storage.putIfAbsent(key, value);
        return Objects.isNull(updatedValue);
    }

    @Override
    public boolean contains(@NotNull String key) {
        return storage.containsKey(key);
    }

}
