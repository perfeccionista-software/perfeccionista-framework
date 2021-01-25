package io.perfeccionista.framework.pagefactory.elements.states.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementStateRegistry {

    private final Map<String, WebElementStateHolder> states;

    private WebElementStateRegistry(Map<String, WebElementStateHolder> states) {
        this.states = states;
    }

    public static WebElementStateRegistry of(Map<String, WebElementStateHolder> states) {
        return new WebElementStateRegistry(states);
    }

    public boolean containsState(String stateName) {
        return states.containsKey(stateName);
    }

    public Optional<WebElementStateHolder> getState(String stateName) {
        return Optional.ofNullable(states.get(stateName));
    }

    public Stream<Entry<String, WebElementStateHolder>> stream() {
        return states.entrySet().stream();
    }

    public void forEach(BiConsumer<String, WebElementStateHolder> consumer) {
        states.forEach(consumer);
    }

    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        this.states.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(entry -> rootNode.set(entry.getKey(), entry.getValue().toJson()));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
