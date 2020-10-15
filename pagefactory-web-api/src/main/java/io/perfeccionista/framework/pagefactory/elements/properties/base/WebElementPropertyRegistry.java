package io.perfeccionista.framework.pagefactory.elements.properties.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementPropertyRegistry {

    private final Map<String, WebElementPropertyHolder> properties;

    private WebElementPropertyRegistry(Map<String, WebElementPropertyHolder> properties) {
        this.properties = properties;
    }

    public static WebElementPropertyRegistry of(Map<String, WebElementPropertyHolder> properties) {
        return new WebElementPropertyRegistry(properties);
    }

    public boolean containsProperty(String propertyName) {
        return properties.containsKey(propertyName);
    }

    public Optional<WebElementPropertyHolder> getProperty(String propertyName) {
        return Optional.ofNullable(properties.get(propertyName));
    }

    public Stream<Entry<String, WebElementPropertyHolder>> stream() {
        return properties.entrySet().stream();
    }

    public void forEach(BiConsumer<String, WebElementPropertyHolder> consumer) {
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

