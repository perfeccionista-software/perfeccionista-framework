package io.perfeccionista.framework.pagefactory.elements.actions.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementActionRegistry {

    private final Map<String, WebElementActionImplementation<?>> actions;

    private WebElementActionRegistry(Map<String, WebElementActionImplementation<?>> actions) {
        this.actions = actions;
    }

    public static WebElementActionRegistry of() {
        return new WebElementActionRegistry(new HashMap<>());
    }

    public static WebElementActionRegistry of(Map<String, WebElementActionImplementation<?>> actions) {
        return new WebElementActionRegistry(actions);
    }

    public WebElementActionRegistry set(String actionName, WebElementActionImplementation<?> actionImplementation) {
        actions.put(actionName, actionImplementation);
        return this;
    }

    public WebElementActionRegistry setIfNotPresent(String actionName, WebElementActionImplementation<?> actionImplementation) {
        if (!actions.containsKey(actionName)) {
            actions.put(actionName, actionImplementation);
        }
        return this;
    }

    public boolean containsAction(String actionName) {
        return actions.containsKey(actionName);
    }

    public <R, T extends WebElementActionImplementation<R>> Optional<T> getAction(String actionName, Class<R> returnType) {
        // TODO: Сделать проверку совместимости типов через утилитный метод
        return (Optional<T>) Optional.ofNullable(actions.get(actionName));
    }

    public <R, T extends WebElementJsOperationActionImplementation<R>> Optional<T> getJsOperationAction(String actionName, Class<R> returnType) {
        // TODO: Сделать проверку совместимости типов через утилитный метод
        return (Optional<T>) Optional.ofNullable(actions.get(actionName))
                .filter(implementation -> implementation instanceof WebElementJsOperationActionImplementation)
                .map(implementation -> (WebElementJsOperationActionImplementation<R>) implementation);
    }

    public Stream<Entry<String, WebElementActionImplementation<?>>> stream() {
        return actions.entrySet().stream();
    }

    public void forEach(BiConsumer<String, WebElementActionImplementation<?>> consumer) {
        actions.forEach(consumer);
    }

    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        this.actions.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(entry -> rootNode.put(entry.getKey(), entry.getValue().getClass().getCanonicalName()));
        return rootNode;
    }

    public String toString() {
        return toJson().toPrettyString();
    }

}
