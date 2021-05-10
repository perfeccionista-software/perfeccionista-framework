package io.perfeccionista.framework.pagefactory.elements.properties.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileElementPropertyRegistry {

    private final Map<String, MobileElementPropertyHolder> properties;

    private MobileElementPropertyRegistry(Map<String, MobileElementPropertyHolder> properties) {
        this.properties = properties;
    }

    public static MobileElementPropertyRegistry of(Map<String, MobileElementPropertyHolder> properties) {
        return new MobileElementPropertyRegistry(properties);
    }

    public boolean containsProperty(String propertyName) {
        return properties.containsKey(propertyName);
    }

    public Optional<MobileElementPropertyHolder> getProperty(String propertyName) {
        return Optional.ofNullable(properties.get(propertyName));
    }

    public Stream<Entry<String, MobileElementPropertyHolder>> stream() {
        return properties.entrySet().stream();
    }

    public void forEach(BiConsumer<String, MobileElementPropertyHolder> consumer) {
        properties.forEach(consumer);
    }

    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        this.properties.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(entry -> rootNode.set(entry.getKey(), entry.getValue().toJson()));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
