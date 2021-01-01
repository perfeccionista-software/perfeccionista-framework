package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

// TODO: Здесь нужно учитывать не только проперти, но и переменные окружения
@Name("config")
public class ConfigDataSource implements DataSource<String, String> {

    private final Map<String, String> propertiesMap;

    public ConfigDataSource() {
        propertiesMap = readPropertiesToMap();
    }

    @Override
    public @NotNull String get(@NotNull String key) {
        return Optional.ofNullable(propertiesMap.get(key))
                .orElse("");
    }

    @Override
    public <T extends String> @Nullable T get(@NotNull String key, @NotNull Class<T> clazz) {
        return (T) get(key);
    }

    @Override
    public @Nullable String getString(@NotNull String key) {
        return get(key);
    }

    private Map<String, String> readPropertiesToMap() {
        Map<String, String> props = new HashMap<>();
        Properties properties = new Properties();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("perfeccionista.properties")) {
            properties.load(inputStream);
            properties.stringPropertyNames().forEach(key -> props.put(key, properties.getProperty(key)));
        } catch (Exception e) {
            // Do nothing: file not found
        }
        return props;
    }

}
