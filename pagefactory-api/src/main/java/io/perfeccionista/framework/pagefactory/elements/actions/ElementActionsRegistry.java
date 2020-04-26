package io.perfeccionista.framework.pagefactory.elements.actions;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class ElementActionsRegistry<T extends ElementActionImplementation<?, ?>> {

    private final Map<String, T> actions;

    private ElementActionsRegistry(Map<String, T> actions) {
        this.actions = actions;
    }

    public static <T extends ElementActionImplementation<?, ?>> ElementActionsRegistry<T> of(Map<String, T> actions) {
        return new ElementActionsRegistry<>(actions);
    }

    public boolean containsAction(String elementMethodName) {
        return actions.containsKey(elementMethodName);
    }

    public <R, T extends ElementActionImplementation<?, R>> T getAction(String actionName, Class<R> returnType) {
        return (T) actions.get(actionName);
    }

    public Stream<Entry<String, T>> stream() {
        return actions.entrySet().stream();
    }

    public void forEach(BiConsumer<String, T> consumer) {
        actions.forEach(consumer);
    }

}
