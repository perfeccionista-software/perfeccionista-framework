package io.perfeccionista.framework.pagefactory.elements.actions.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.EndpointHandlerNotFound;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ENDPOINT_HANDLER_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class EndpointHandlerRegistry {

    private final Map<String, Class<? extends EndpointHandler>> handlers;

    private EndpointHandlerRegistry(Map<String, Class<? extends EndpointHandler>> handlers) {
        this.handlers = handlers;
    }

    public static EndpointHandlerRegistry of() {
        return new EndpointHandlerRegistry(new HashMap<>());
    }

    public static EndpointHandlerRegistry of(Map<String, Class<? extends EndpointHandler>> handlers) {
        return new EndpointHandlerRegistry(handlers);
    }

    public EndpointHandlerRegistry set(String actionName, Class<? extends EndpointHandler<?>> handler) {
        handlers.put(actionName, handler);
        return this;
    }

    public EndpointHandlerRegistry setIfNotPresent(String actionName, Class<? extends EndpointHandler<?>> handler) {
        if (!handlers.containsKey(actionName)) {
            handlers.put(actionName, handler);
        }
        return this;
    }

    public boolean containsAction(String actionName) {
        return handlers.containsKey(actionName);
    }

    public <R, T extends EndpointHandler<R>> Class<T> getEndpointHandler(String actionName,
                                                                         Class<R> returnType) {
        // TODO: Check generic type of EndpointHandler
        return (Class<T>) Optional.ofNullable(handlers.get(actionName))
                .orElseThrow(() -> EndpointHandlerNotFound.exception(ENDPOINT_HANDLER_NOT_FOUND.getMessage(actionName)));
    }

    public Stream<Entry<String, Class<? extends EndpointHandler>>> stream() {
        return handlers.entrySet().stream();
    }

    public void forEach(BiConsumer<String, Class<? extends EndpointHandler>> consumer) {
        handlers.forEach(consumer);
    }

    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        this.handlers.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(entry -> rootNode.put(entry.getKey(), entry.getValue().getCanonicalName()));
        return rootNode;
    }

    public String toString() {
        return toJson().toPrettyString();
    }

}
