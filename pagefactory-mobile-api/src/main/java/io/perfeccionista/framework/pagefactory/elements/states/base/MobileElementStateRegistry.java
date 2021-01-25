package io.perfeccionista.framework.pagefactory.elements.states.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileElementStateRegistry {

    private final Map<String, MobileElementStateHolder> states;

    private MobileElementStateRegistry(Map<String, MobileElementStateHolder> states) {
        this.states = states;
    }

    public static MobileElementStateRegistry of(Map<String, MobileElementStateHolder> states) {
        return new MobileElementStateRegistry(states);
    }

    public boolean containsState(String stateName) {
        return states.containsKey(stateName);
    }

    public Optional<MobileElementStateHolder> getState(String stateName) {
        return Optional.ofNullable(states.get(stateName));
    }

    public Stream<Entry<String, MobileElementStateHolder>> stream() {
        return states.entrySet().stream();
    }

    public void forEach(BiConsumer<String, MobileElementStateHolder> consumer) {
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
