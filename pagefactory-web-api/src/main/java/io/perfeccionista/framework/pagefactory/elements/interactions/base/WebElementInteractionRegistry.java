package io.perfeccionista.framework.pagefactory.elements.interactions.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementInteractionRegistry {

    private final Map<String, WebElementInteractionImplementation> interactions;

    private WebElementInteractionRegistry(Map<String, WebElementInteractionImplementation> interactions) {
        this.interactions = interactions;
    }

    public static WebElementInteractionRegistry of(Map<String, WebElementInteractionImplementation> actions) {
        return new WebElementInteractionRegistry(actions);
    }

    public boolean containsInteraction(String elementMethodName) {
        return interactions.containsKey(elementMethodName);
    }

    public <T extends WebElementInteractionImplementation> Optional<T> getInteraction(String interactionName) {
        return (Optional<T>) Optional.ofNullable(interactions.get(interactionName));
    }

    public Stream<Entry<String, WebElementInteractionImplementation>> stream() {
        return interactions.entrySet().stream();
    }

    public void forEach(BiConsumer<String, WebElementInteractionImplementation> consumer) {
        interactions.forEach(consumer);
    }

    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        this.interactions.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(entry -> rootNode.put(entry.getKey(), entry.getValue().getClass().getCanonicalName()));
        return rootNode;
    }

    public String toString() {
        return toJson().toPrettyString();
    }

}
