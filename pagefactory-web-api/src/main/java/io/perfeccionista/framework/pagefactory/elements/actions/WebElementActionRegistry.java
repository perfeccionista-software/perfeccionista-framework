package io.perfeccionista.framework.pagefactory.elements.actions;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class WebElementActionRegistry {

    private final Map<String, WebElementActionImplementation<?>> actions;

    private WebElementActionRegistry(Map<String, WebElementActionImplementation<?>> actions) {
        this.actions = actions;
    }

    public static WebElementActionRegistry of(Map<String, WebElementActionImplementation<?>> actions) {
        return new WebElementActionRegistry(actions);
    }

    public boolean containsAction(String elementMethodName) {
        return actions.containsKey(elementMethodName);
    }

    public <R, T extends WebElementActionImplementation<R>> Optional<T> getAction(String actionName, Class<R> returnType) {
        return (Optional<T>) Optional.ofNullable(actions.get(actionName));
    }

    public Stream<Entry<String, WebElementActionImplementation<?>>> stream() {
        return actions.entrySet().stream();
    }

    public void forEach(BiConsumer<String, WebElementActionImplementation<?>> consumer) {
        actions.forEach(consumer);
    }

}
