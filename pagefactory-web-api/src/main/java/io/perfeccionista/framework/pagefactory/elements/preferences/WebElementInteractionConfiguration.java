package io.perfeccionista.framework.pagefactory.elements.preferences;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.WebElementInteractionNotFound;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.interactions.base.WebElementInteractionImplementation;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_INTERACTION_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementInteractionConfiguration implements JsonSerializable {

    private final HashMap<String, WebElementInteractionImplementation> interactionImplementations = new HashMap<>();

    private WebElementInteractionConfiguration() {}

    public static WebElementInteractionConfiguration builder() {
        return new WebElementInteractionConfiguration();
    }

    public static WebElementInteractionConfiguration buildFrom(@NotNull WebElementInteractionConfiguration baseConfiguration) {
        WebElementInteractionConfiguration configuration = new WebElementInteractionConfiguration();
        baseConfiguration.stream().forEach(entry -> configuration.set(entry.getKey(), entry.getValue()));
        return configuration;
    }

    public WebElementInteractionConfiguration set(@NotNull String interactionName, @NotNull WebElementInteractionImplementation interactionImplementation) {
        interactionImplementations.put(interactionName, interactionImplementation);
        return this;
    }

    public WebElementInteractionConfiguration setIfNotPresent(@NotNull String interactionName, @NotNull WebElementInteractionImplementation interactionImplementation) {
        if (!interactionImplementations.containsKey(interactionName)) {
            interactionImplementations.put(interactionName, interactionImplementation);
        }
        return this;
    }

    public WebElementInteractionConfiguration setFrom(@NotNull WebElementInteractionConfiguration configuration) {
        configuration.stream().forEach(entry -> set(entry.getKey(), entry.getValue()));
        return this;
    }

    public WebElementInteractionConfiguration setFromIfNotPresent(@NotNull WebElementInteractionConfiguration configuration) {
        configuration.stream().forEach(entry -> setIfNotPresent(entry.getKey(), entry.getValue()));
        return this;
    }

    public WebElementInteractionImplementation getInteractionImplementation(@NotNull String interactionName) {
        return Optional.ofNullable(interactionImplementations.get(interactionName))
                .orElseThrow(() -> WebElementInteractionNotFound.exception(ELEMENT_INTERACTION_NOT_FOUND.getMessage(interactionName)));
    }

    public Map<String, WebElementInteractionImplementation> asMap() {
        return new HashMap<>(interactionImplementations);
    }

    public Stream<Entry<String, WebElementInteractionImplementation>> stream() {
        return interactionImplementations.entrySet().stream();
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        interactionImplementations.forEach((name, implementation) -> rootNode.put(name, implementation.getClass().getCanonicalName()));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
