package io.perfeccionista.framework.pagefactory.elements.preferences;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.LocatorNotFound;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_LOCATOR_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebLocatorConfiguration implements JsonSerializable {

    private final HashMap<String, WebLocatorHolder> webLocatorHolders = new HashMap<>();

    private WebLocatorConfiguration() {}

    public static WebLocatorConfiguration builder() {
        return new WebLocatorConfiguration();
    }

    public static WebLocatorConfiguration buildFrom(@NotNull WebLocatorConfiguration baseConfiguration) {
        WebLocatorConfiguration configuration = new WebLocatorConfiguration();
        baseConfiguration.stream().forEach(entry -> configuration.set(entry.getKey(), entry.getValue()));
        return configuration;
    }

    public WebLocatorConfiguration set(@NotNull String locatorName, @NotNull WebLocatorHolder locatorHolder) {
        webLocatorHolders.put(locatorName, locatorHolder);
        return this;
    }

    public WebLocatorConfiguration setIfNotPresent(@NotNull String locatorName, @NotNull WebLocatorHolder locatorHolder) {
        if (!webLocatorHolders.containsKey(locatorName)) {
            webLocatorHolders.put(locatorName, locatorHolder);
        }
        return this;
    }

    public WebLocatorConfiguration setFrom(@NotNull WebLocatorConfiguration configuration) {
        configuration.stream().forEach(entry -> set(entry.getKey(), entry.getValue()));
        return this;
    }

    public WebLocatorConfiguration setFromIfNotPresent(@NotNull WebLocatorConfiguration configuration) {
        configuration.stream().forEach(entry -> setIfNotPresent(entry.getKey(), entry.getValue()));
        return this;
    }

    public WebLocatorHolder getLocatorHolder(@NotNull String locatorName) {
        return Optional.ofNullable(webLocatorHolders.get(locatorName))
                .orElseThrow(() -> LocatorNotFound.exception(ELEMENT_LOCATOR_NOT_FOUND.getMessage(locatorName)));
    }

    public Map<String, WebLocatorHolder> asMap() {
        return new HashMap<>(webLocatorHolders);
    }

    public Stream<Entry<String, WebLocatorHolder>> stream() {
        return webLocatorHolders.entrySet().stream();
    }

    @Override
    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        webLocatorHolders.forEach((name, implementation) -> rootNode.put(name, implementation.getClass().getCanonicalName()));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
