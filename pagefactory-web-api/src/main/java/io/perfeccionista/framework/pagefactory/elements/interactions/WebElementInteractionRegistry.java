package io.perfeccionista.framework.pagefactory.elements.interactions;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

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

}
