package io.perfeccionista.framework.pagefactory.configurations.datasource;

import io.perfeccionista.framework.datasource.DataSource;
import io.perfeccionista.framework.exceptions.EmptyPath;
import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@Name("path to resource file")
public class TestResourceFileToPathDataSource implements DataSource<String, String> {

    @Override
    public @Nullable String get(@NotNull String key) {
        return Optional.ofNullable(this.getClass().getClassLoader().getResource("test.data.properties"))
                .orElseThrow(() -> EmptyPath.exception(String.format("No file with name %s found in resources dir", key)))
                .getPath();
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

