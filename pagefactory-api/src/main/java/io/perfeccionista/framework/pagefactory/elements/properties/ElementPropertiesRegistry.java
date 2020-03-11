package io.perfeccionista.framework.pagefactory.elements.properties;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class ElementPropertiesRegistry<T extends ElementPropertyHolder> {

    private final Map<String, T> properties;

    private ElementPropertiesRegistry(Map<String, T> properties) {
        this.properties = properties;
    }

    public static <T extends ElementPropertyHolder> ElementPropertiesRegistry<T> of(Map<String, T> properties) {
        return new ElementPropertiesRegistry<>(properties);
    }

    public boolean containsElementProperty(String propertyName) {
        return properties.containsKey(propertyName);
    }

    public Optional<T> getElementProperty(String propertyName) {
        return Optional.ofNullable(properties.get(propertyName));
    }

    public Stream<Entry<String, T>> stream() {
        return properties.entrySet().stream();
    }

    public void forEach(BiConsumer<String, T> consumer) {
        properties.forEach(consumer);
    }
}
