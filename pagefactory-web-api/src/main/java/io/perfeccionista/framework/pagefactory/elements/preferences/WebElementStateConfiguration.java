package io.perfeccionista.framework.pagefactory.elements.preferences;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.ElementStateNotFound;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.states.base.WebElementStateHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_PROPERTY_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementStateConfiguration implements JsonSerializable {

    private final HashMap<String, WebElementStateHolder> states = new HashMap<>();

    private WebElementStateConfiguration() {}

    public static WebElementStateConfiguration builder() {
        return new WebElementStateConfiguration();
    }

    public static WebElementStateConfiguration buildFrom(@NotNull WebElementStateConfiguration baseConfiguration) {
        WebElementStateConfiguration configuration = new WebElementStateConfiguration();
        baseConfiguration.stream().forEach(entry -> configuration.set(entry.getKey(), entry.getValue()));
        return configuration;
    }

    public WebElementStateConfiguration set(@NotNull String stateName, @NotNull WebElementStateHolder stateHolder) {
        states.put(stateName, stateHolder);
        return this;
    }

    public WebElementStateConfiguration setIfNotPresent(@NotNull String stateName, @NotNull WebElementStateHolder stateHolder) {
        if (!states.containsKey(stateName)) {
            states.put(stateName, stateHolder);
        }
        return this;
    }

    public WebElementStateConfiguration setFrom(@NotNull WebElementStateConfiguration configuration) {
        configuration.stream().forEach(entry -> set(entry.getKey(), entry.getValue()));
        return this;
    }

    public WebElementStateConfiguration setFromIfNotPresent(@NotNull WebElementStateConfiguration configuration) {
        configuration.stream().forEach(entry -> setIfNotPresent(entry.getKey(), entry.getValue()));
        return this;
    }

    public WebElementStateHolder getStateHolder(@NotNull String stateName) {
        return Optional.ofNullable(states.get(stateName))
                .orElseThrow(() -> ElementStateNotFound.exception(ELEMENT_PROPERTY_NOT_FOUND.getMessage(stateName)));
    }

    public Map<String, WebElementStateHolder> asMap() {
        return new HashMap<>(states);
    }

    public Stream<Entry<String, WebElementStateHolder>> stream() {
        return states.entrySet().stream();
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        states.forEach((name, implementation) -> rootNode.put(name, implementation.getClass().getCanonicalName()));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}

