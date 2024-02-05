package io.perfeccionista.framework.datasource;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.utils.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static io.perfeccionista.framework.Environment.PERFECCIONISTA_PROPERTIES_FILE;

@Name("config")
public class ConfigDataSource implements DataSource<String, String> {

    private static volatile boolean cacheReady = false;
    private static final Map<String, String> fileProperties = new HashMap<>();
    private final Map<String, String> properties = new HashMap<>();

    public ConfigDataSource() {
        readFilePropertiesToMap();
        readSystemPropertiesToMap();
    }

    @Override
    public @NotNull String get(@NotNull String key) {
        return Optional.ofNullable(properties.get(key))
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

    private synchronized void readFilePropertiesToMap() {
        if (!cacheReady) {
            Properties perfeccionistaProperties = FileUtils.readOptionalPropertyFileFromClasspath(PERFECCIONISTA_PROPERTIES_FILE)
                    .orElse(new Properties());
            perfeccionistaProperties.stringPropertyNames()
                    .forEach(key -> fileProperties.put(key, perfeccionistaProperties.getProperty(key)));
            cacheReady = true;
        }
        properties.putAll(fileProperties);
    }

    private void readSystemPropertiesToMap() {
        Properties systemProperties = System.getProperties();
        systemProperties.stringPropertyNames()
                .forEach(key -> properties.put(key, systemProperties.getProperty(key)));
    }

}
