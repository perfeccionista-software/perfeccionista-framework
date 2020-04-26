package io.perfeccionista.framework.pagefactory.elements.interactions;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class ElementInteractionsRegistry<T extends ElementInteractionImplementation<?, ?, ?>> {

    private final Map<String, T> interactions;

    private ElementInteractionsRegistry(Map<String, T> interactions) {
        this.interactions = interactions;
    }

    public static <T extends ElementInteractionImplementation<?, ?, ?>> ElementInteractionsRegistry<T> of(Map<String, T> interactions) {
        return new ElementInteractionsRegistry<>(interactions);
    }

    public boolean containsInteraction(String interactionName) {
        return interactions.containsKey(interactionName);
    }

    public <R, T extends ElementInteractionImplementation<?, ?, R>> T getInteraction(String interactionName, Class<R> returnType) {
        return (T) interactions.get(interactionName);
    }

    public Stream<Entry<String, T>> stream() {
        return interactions.entrySet().stream();
    }

    public void forEach(BiConsumer<String, T> consumer) {
        interactions.forEach(consumer);
    }

}
