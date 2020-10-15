package io.perfeccionista.framework.pagefactory.elements.preferences;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.WebElementActionNotFound;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebElementActionImplementation;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebApiMessages.ELEMENT_ACTION_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementActionConfiguration implements JsonSerializable {

    private final HashMap<String, WebElementActionImplementation<?>> actionImplementations = new HashMap<>();

    private WebElementActionConfiguration() {}

    public static WebElementActionConfiguration builder() {
        return new WebElementActionConfiguration();
    }

    public static WebElementActionConfiguration buildFrom(@NotNull WebElementActionConfiguration baseConfiguration) {
        WebElementActionConfiguration configuration = new WebElementActionConfiguration();
        baseConfiguration.stream().forEach(entry -> configuration.set(entry.getKey(), entry.getValue()));
        return configuration;
    }

    public WebElementActionConfiguration set(@NotNull String actionName, @NotNull WebElementActionImplementation<?> actionImplementation) {
        actionImplementations.put(actionName, actionImplementation);
        return this;
    }

    public WebElementActionConfiguration setIfNotPresent(@NotNull String actionName, @NotNull WebElementActionImplementation<?> actionImplementation) {
        if (!actionImplementations.containsKey(actionName)) {
            actionImplementations.put(actionName, actionImplementation);
        }
        return this;
    }

    public WebElementActionConfiguration setFrom(@NotNull WebElementActionConfiguration configuration) {
        configuration.stream().forEach(entry -> set(entry.getKey(), entry.getValue()));
        return this;
    }

    public WebElementActionConfiguration setFromIfNotPresent(@NotNull WebElementActionConfiguration configuration) {
        configuration.stream().forEach(entry -> setIfNotPresent(entry.getKey(), entry.getValue()));
        return this;
    }

    public WebElementActionImplementation<?> getActionImplementation(@NotNull String actionName) {
        return Optional.ofNullable(actionImplementations.get(actionName))
                .orElseThrow(() -> WebElementActionNotFound.exception(ELEMENT_ACTION_NOT_FOUND.getMessage(actionName)));
    }

    public Map<String, WebElementActionImplementation<?>> asMap() {
        return new HashMap<>(actionImplementations);
    }

    public Stream<Entry<String, WebElementActionImplementation<?>>> stream() {
        return actionImplementations.entrySet().stream();
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        actionImplementations.forEach((name, implementation) -> rootNode.put(name, implementation.getClass().getCanonicalName()));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
