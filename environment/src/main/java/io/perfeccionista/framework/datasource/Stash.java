package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.ValueNotFound;
import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static io.perfeccionista.framework.exceptions.messages.EnvironmentMessages.DATA_SOURCE_VALUE_NOT_FOUND;

@Name("stash")
public final class Stash implements ObjectDataStorage<String, Object> {

    private final Map<String, Object> storage = new HashMap<>();

    public static Stash stash() {
        return Environment.getForCurrentThread()
                .getService(DataSourceService.class)
                .get(Stash.class);
    }

    @Override
    public boolean contains(@NotNull String key) {
        return storage.containsKey(key);
    }

    @Override
    public @NotNull Object get(@NotNull String key) {
        return Optional.ofNullable(storage.get(key))
                .orElseThrow(() -> ValueNotFound.exception(DATA_SOURCE_VALUE_NOT_FOUND.getMessage(this.getClass().getCanonicalName(), key)));
    }

    @Override
    public Optional<Object> getOptional(@NotNull String key) {
        return Optional.ofNullable(storage.get(key));
    }

    @Override
    public <T> @NotNull T get(@NotNull String key, @NotNull Class<T> clazz) {
        return Optional.ofNullable((T) get(key))
                .orElseThrow(() -> ValueNotFound.exception(DATA_SOURCE_VALUE_NOT_FOUND.getMessage(this.getClass().getCanonicalName(), key)));
    }

    @Override
    public <T> Optional<T> getOptional(@NotNull String key, @NotNull Class<T> clazz) {
        return Optional.ofNullable((T) get(key));
    }

    @Override
    public @NotNull String getString(@NotNull String key) {
        return getOptional(key)
                .map(Object::toString)
                .orElseThrow(() -> ValueNotFound.exception(DATA_SOURCE_VALUE_NOT_FOUND.getMessage(this.getClass().getCanonicalName(), key)));
    }

    @Override
    public Optional<String> getOptionalString(@NotNull String key) {
        return getOptional(key)
                .map(Object::toString);
    }

    @Override
    public Stash put(@NotNull String key, @Nullable Object value) {
        storage.put(key, value);
        return this;
    }

    @Override
    public boolean putIfAbsent(@NotNull String key, @Nullable Object value) {
        Object updatedValue = storage.putIfAbsent(key, value);
        return Objects.isNull(updatedValue);
    }

}
