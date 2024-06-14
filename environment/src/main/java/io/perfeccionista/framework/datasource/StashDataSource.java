package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Name("stash")
public final class StashDataSource implements ObjectDataStorage<String, Object> {

    private final Map<String, Object> storage = new HashMap<>();

    @Override
    public boolean contains(@NotNull String key) {
        return storage.containsKey(key);
    }

    @Override
    public Optional<Object> get(@NotNull String key) {
        return Optional.ofNullable(storage.get(key));
    }

    @Override
    public <T> Optional<T> get(@NotNull String key, @NotNull Class<T> clazz) {
        return Optional.ofNullable((T) get(key));
    }

    @Override
    public Optional<String> getString(@NotNull String key) {
        Object result = get(key);
        return result == null
                ? Optional.empty()
                : Optional.of(result.toString());
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

}
