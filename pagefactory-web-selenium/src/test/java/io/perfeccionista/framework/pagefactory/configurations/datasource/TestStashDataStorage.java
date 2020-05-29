package io.perfeccionista.framework.pagefactory.configurations.datasource;

import io.perfeccionista.framework.datasource.DataStorage;
import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

@Name("stash")
public class TestStashDataStorage implements DataStorage<String, String> {

    private Map<String, String> stash = new HashMap<>();

    @Override
    public void put(@NotNull String key, @Nullable String value) {
        stash.put(key, value);
    }

    @Override
    public boolean putIfAbsent(@NotNull String key, @Nullable String value) {
        String stashValue = stash.putIfAbsent(key, value);
        return stashValue != null;
    }

    @Override
    public boolean contains(@NotNull String key) {
        return stash.containsKey(key);
    }

    @Nullable
    @Override
    public String get(@NotNull String key) {
        return stash.get(key);
    }

    @Override
    public <T extends String> @Nullable T get(@NotNull String key, @NotNull Class<T> clazz) {
        return (T) get(key);
    }

    @Override
    public @Nullable String getString(@NotNull String key) {
        return get(key);
    }

}
