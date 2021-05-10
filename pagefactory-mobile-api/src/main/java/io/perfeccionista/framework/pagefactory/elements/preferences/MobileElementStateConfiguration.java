package io.perfeccionista.framework.pagefactory.elements.preferences;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.ElementStateNotFound;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.states.base.MobileElementStateHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_PROPERTY_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileElementStateConfiguration implements JsonSerializable {

    private final HashMap<String, MobileElementStateHolder> states = new HashMap<>();

    private MobileElementStateConfiguration() {}

    public static MobileElementStateConfiguration builder() {
        return new MobileElementStateConfiguration();
    }

    public static MobileElementStateConfiguration buildFrom(@NotNull MobileElementStateConfiguration baseConfiguration) {
        MobileElementStateConfiguration configuration = new MobileElementStateConfiguration();
        baseConfiguration.stream().forEach(entry -> configuration.set(entry.getKey(), entry.getValue()));
        return configuration;
    }

    public MobileElementStateConfiguration set(@NotNull String stateName, @NotNull MobileElementStateHolder stateHolder) {
        states.put(stateName, stateHolder);
        return this;
    }

    public MobileElementStateConfiguration setIfNotPresent(@NotNull String stateName, @NotNull MobileElementStateHolder stateHolder) {
        if (!states.containsKey(stateName)) {
            states.put(stateName, stateHolder);
        }
        return this;
    }

    public MobileElementStateConfiguration setFrom(@NotNull MobileElementStateConfiguration configuration) {
        configuration.stream().forEach(entry -> set(entry.getKey(), entry.getValue()));
        return this;
    }

    public MobileElementStateConfiguration setFromIfNotPresent(@NotNull MobileElementStateConfiguration configuration) {
        configuration.stream().forEach(entry -> setIfNotPresent(entry.getKey(), entry.getValue()));
        return this;
    }

    public MobileElementStateHolder getStateHolder(@NotNull String stateName) {
        return Optional.ofNullable(states.get(stateName))
                .orElseThrow(() -> ElementStateNotFound.exception(ELEMENT_PROPERTY_NOT_FOUND.getMessage(stateName)));
    }

    public Map<String, MobileElementStateHolder> asMap() {
        return new HashMap<>(states);
    }

    public Stream<Entry<String, MobileElementStateHolder>> stream() {
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
