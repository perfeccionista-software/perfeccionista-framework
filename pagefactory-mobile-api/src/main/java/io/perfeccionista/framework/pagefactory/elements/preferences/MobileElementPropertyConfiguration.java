package io.perfeccionista.framework.pagefactory.elements.preferences;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.ElementPropertyNotFound;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.properties.base.MobileElementPropertyHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_PROPERTY_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileElementPropertyConfiguration implements JsonSerializable {

    private final HashMap<String, MobileElementPropertyHolder> properties = new HashMap<>();

    private MobileElementPropertyConfiguration() {}

    public static MobileElementPropertyConfiguration builder() {
        return new MobileElementPropertyConfiguration();
    }

    public static MobileElementPropertyConfiguration buildFrom(@NotNull MobileElementPropertyConfiguration baseConfiguration) {
        MobileElementPropertyConfiguration configuration = new MobileElementPropertyConfiguration();
        baseConfiguration.stream().forEach(entry -> configuration.set(entry.getKey(), entry.getValue()));
        return configuration;
    }

    public MobileElementPropertyConfiguration set(@NotNull String propertyName, @NotNull MobileElementPropertyHolder propertyHolder) {
        properties.put(propertyName, propertyHolder);
        return this;
    }

    public MobileElementPropertyConfiguration setIfNotPresent(@NotNull String propertyName, @NotNull MobileElementPropertyHolder propertyHolder) {
        if (!properties.containsKey(propertyName)) {
            properties.put(propertyName, propertyHolder);
        }
        return this;
    }

    public MobileElementPropertyConfiguration setFrom(@NotNull MobileElementPropertyConfiguration configuration) {
        configuration.stream().forEach(entry -> set(entry.getKey(), entry.getValue()));
        return this;
    }

    public MobileElementPropertyConfiguration setFromIfNotPresent(@NotNull MobileElementPropertyConfiguration configuration) {
        configuration.stream().forEach(entry -> setIfNotPresent(entry.getKey(), entry.getValue()));
        return this;
    }

    public MobileElementPropertyHolder getPropertyHolder(@NotNull String propertyName) {
        return Optional.ofNullable(properties.get(propertyName))
                .orElseThrow(() -> ElementPropertyNotFound.exception(ELEMENT_PROPERTY_NOT_FOUND.getMessage(propertyName)));
    }

    public Map<String, MobileElementPropertyHolder> asMap() {
        return new HashMap<>(properties);
    }

    public Stream<Entry<String, MobileElementPropertyHolder>> stream() {
        return properties.entrySet().stream();
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        properties.forEach((name, implementation) -> rootNode.put(name, implementation.getClass().getCanonicalName()));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
