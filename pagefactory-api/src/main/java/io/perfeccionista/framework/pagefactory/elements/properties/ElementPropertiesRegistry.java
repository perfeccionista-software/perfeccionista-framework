package io.perfeccionista.framework.pagefactory.elements.properties;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class ElementPropertiesRegistry {

    private final Map<String, ElementPropertyHolder> properties;

    private ElementPropertiesRegistry(Map<String, ElementPropertyHolder> properties) {
        this.properties = properties;
    }

    public static ElementPropertiesRegistry of(Map<String, ElementPropertyHolder> properties) {
        return new ElementPropertiesRegistry(properties);
    }

    public boolean containsElementProperty(String propertyName) {
        return properties.containsKey(propertyName);
    }

    public Optional<ElementPropertyHolder> getElementProperty(String propertyName) {
        return Optional.ofNullable(properties.get(propertyName));
    }

    public Stream<Entry<String, ElementPropertyHolder>> stream() {
        return properties.entrySet().stream();
    }

    public void forEach(BiConsumer<String, ElementPropertyHolder> consumer) {
        properties.forEach(consumer);
    }
}
