package io.perfeccionista.framework.pagefactory.configurations.datasource;

import io.perfeccionista.framework.datasource.DataSource;
import io.perfeccionista.framework.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Name("props")
public class TestPropertiesDataSource implements DataSource<String, String> {

    private final Map<String, String> propertiesMap;

    public TestPropertiesDataSource() {
        propertiesMap = readPropertiesToMap("test.data.properties");
    }

    @Override
    public @Nullable String get(@NotNull String key) {
        return propertiesMap.get(key);
    }

    @Override
    public <T extends String> @Nullable T get(@NotNull String key, @NotNull Class<T> clazz) {
        return (T) get(key);
    }

    @Override
    public @Nullable String getString(@NotNull String key) {
        return get(key);
    }

    private Map<String, String> readPropertiesToMap(String path) {
        Map<String, String> props = new HashMap<>();
        Properties properties = new Properties();
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path)) {
            properties.load(inputStream);
            properties.stringPropertyNames().forEach(key -> props.put(key, properties.getProperty(key)));
        } catch (Exception e) {
            throw new RuntimeException("Can't read properties from file " + path);
        }
        return props;
    }

}
