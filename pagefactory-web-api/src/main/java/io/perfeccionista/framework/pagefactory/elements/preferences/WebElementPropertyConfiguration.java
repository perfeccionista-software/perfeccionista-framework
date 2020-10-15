package io.perfeccionista.framework.pagefactory.elements.preferences;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.WebElementPropertyNotFound;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_PROPERTY_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementPropertyConfiguration implements JsonSerializable {

    private final HashMap<String, WebElementPropertyHolder> properties = new HashMap<>();

    private WebElementPropertyConfiguration() {}

    public static WebElementPropertyConfiguration builder() {
        return new WebElementPropertyConfiguration();
    }

    public static WebElementPropertyConfiguration buildFrom(@NotNull WebElementPropertyConfiguration baseConfiguration) {
        WebElementPropertyConfiguration configuration = new WebElementPropertyConfiguration();
        baseConfiguration.stream().forEach(entry -> configuration.set(entry.getKey(), entry.getValue()));
        return configuration;
    }

    public WebElementPropertyConfiguration set(@NotNull String propertyName, @NotNull WebElementPropertyHolder propertyHolder) {
        properties.put(propertyName, propertyHolder);
        return this;
    }

    public WebElementPropertyConfiguration setIfNotPresent(@NotNull String propertyName, @NotNull WebElementPropertyHolder propertyHolder) {
        if (!properties.containsKey(propertyName)) {
            properties.put(propertyName, propertyHolder);
        }
        return this;
    }

    public WebElementPropertyConfiguration setFrom(@NotNull WebElementPropertyConfiguration configuration) {
        configuration.stream().forEach(entry -> set(entry.getKey(), entry.getValue()));
        return this;
    }

    public WebElementPropertyConfiguration setFromIfNotPresent(@NotNull WebElementPropertyConfiguration configuration) {
        configuration.stream().forEach(entry -> setIfNotPresent(entry.getKey(), entry.getValue()));
        return this;
    }

    public WebElementPropertyHolder getPropertyHolder(@NotNull String propertyName) {
        return Optional.ofNullable(properties.get(propertyName))
                .orElseThrow(() -> WebElementPropertyNotFound.exception(ELEMENT_PROPERTY_NOT_FOUND.getMessage(propertyName)));
    }

    public Map<String, WebElementPropertyHolder> asMap() {
        return new HashMap<>(properties);
    }

    public Stream<Entry<String, WebElementPropertyHolder>> stream() {
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
