package io.perfeccionista.framework.pagefactory.elements.states;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class ElementStatesRegistry {

    private final Map<String, ElementStateHolder> states;

    private ElementStatesRegistry(Map<String, ElementStateHolder> states) {
        this.states = states;
    }

    public static ElementStatesRegistry of(Map<String, ElementStateHolder> states) {
        return new ElementStatesRegistry(states);
    }

    public boolean containsElementState(String stateName) {
        return states.containsKey(stateName);
    }

    public Optional<ElementStateHolder> getElementState(String stateName) {
        return Optional.ofNullable(states.get(stateName));
    }

    public Stream<Entry<String, ElementStateHolder>> stream() {
        return states.entrySet().stream();
    }

    public void forEach(BiConsumer<String, ElementStateHolder> consumer) {
        states.forEach(consumer);
    }

}
