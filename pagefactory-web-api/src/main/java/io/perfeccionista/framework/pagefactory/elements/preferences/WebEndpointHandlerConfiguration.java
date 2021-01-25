package io.perfeccionista.framework.pagefactory.elements.preferences;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.EndpointHandlerNotFound;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.operation.handler.EndpointHandler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ENDPOINT_HANDLER_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebEndpointHandlerConfiguration implements JsonSerializable {

    private final HashMap<String, Class<? extends EndpointHandler>> endpointHandlers = new HashMap<>();

    private WebEndpointHandlerConfiguration() {}

    public static WebEndpointHandlerConfiguration builder() {
        return new WebEndpointHandlerConfiguration();
    }

    public static WebEndpointHandlerConfiguration buildFrom(@NotNull WebEndpointHandlerConfiguration baseConfiguration) {
        WebEndpointHandlerConfiguration configuration = new WebEndpointHandlerConfiguration();
        baseConfiguration.stream().forEach(entry -> configuration.set(entry.getKey(), entry.getValue()));
        return configuration;
    }

    public WebEndpointHandlerConfiguration set(@NotNull String actionName, @NotNull Class<? extends EndpointHandler> endpointHandler) {
        endpointHandlers.put(actionName, endpointHandler);
        return this;
    }

    public WebEndpointHandlerConfiguration setIfNotPresent(@NotNull String actionName, @NotNull Class<? extends EndpointHandler> endpointHandler) {
        if (!endpointHandlers.containsKey(actionName)) {
            endpointHandlers.put(actionName, endpointHandler);
        }
        return this;
    }

    public WebEndpointHandlerConfiguration setFrom(@NotNull WebEndpointHandlerConfiguration configuration) {
        configuration.stream().forEach(entry -> set(entry.getKey(), entry.getValue()));
        return this;
    }

    public WebEndpointHandlerConfiguration setFromIfNotPresent(@NotNull WebEndpointHandlerConfiguration configuration) {
        configuration.stream().forEach(entry -> setIfNotPresent(entry.getKey(), entry.getValue()));
        return this;
    }

    public Class<? extends EndpointHandler> getActionImplementation(@NotNull String actionName) {
        return Optional.ofNullable(endpointHandlers.get(actionName))
                .orElseThrow(() -> EndpointHandlerNotFound.exception(ENDPOINT_HANDLER_NOT_FOUND.getMessage(actionName)));
    }

    public Map<String, Class<? extends EndpointHandler>> asMap() {
        return new HashMap<>(endpointHandlers);
    }

    public Stream<Entry<String, Class<? extends EndpointHandler>>> stream() {
        return endpointHandlers.entrySet().stream();
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        endpointHandlers.forEach((name, implementation) -> rootNode.put(name, implementation.getCanonicalName()));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
