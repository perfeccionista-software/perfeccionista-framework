package io.perfeccionista.framework.pagefactory.elements.preferences;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.LocatorNotFound;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.locators.AndroidLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.IosLocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.ELEMENT_LOCATOR_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileLocatorConfiguration implements JsonSerializable {

    private final HashMap<String, AndroidLocatorHolder> androidLocatorHolders = new HashMap<>();
    private final HashMap<String, IosLocatorHolder> iosLocatorHolders = new HashMap<>();

    private MobileLocatorConfiguration() {}

    public static MobileLocatorConfiguration builder() {
        return new MobileLocatorConfiguration();
    }

    public static MobileLocatorConfiguration buildFrom(@NotNull MobileLocatorConfiguration baseConfiguration) {
        MobileLocatorConfiguration configuration = new MobileLocatorConfiguration();
        baseConfiguration.streamForAndroid().forEach(entry -> configuration.setForAndroid(entry.getKey(), entry.getValue()));
        baseConfiguration.streamForIos().forEach(entry -> configuration.setForIos(entry.getKey(), entry.getValue()));
        return configuration;
    }

    public MobileLocatorConfiguration setForAndroid(@NotNull String locatorName, @NotNull AndroidLocatorHolder locatorHolder) {
        androidLocatorHolders.put(locatorName, locatorHolder);
        return this;
    }

    public MobileLocatorConfiguration setForIos(@NotNull String locatorName, @NotNull IosLocatorHolder locatorHolder) {
        iosLocatorHolders.put(locatorName, locatorHolder);
        return this;
    }

    public MobileLocatorConfiguration setForAndroidIfNotPresent(@NotNull String locatorName, @NotNull AndroidLocatorHolder locatorHolder) {
        if (!androidLocatorHolders.containsKey(locatorName)) {
            androidLocatorHolders.put(locatorName, locatorHolder);
        }
        return this;
    }

    public MobileLocatorConfiguration setForIosIfNotPresent(@NotNull String locatorName, @NotNull IosLocatorHolder locatorHolder) {
        if (!iosLocatorHolders.containsKey(locatorName)) {
            iosLocatorHolders.put(locatorName, locatorHolder);
        }
        return this;
    }

    public MobileLocatorConfiguration setFrom(@NotNull MobileLocatorConfiguration configuration) {
        configuration.streamForAndroid().forEach(entry -> setForAndroid(entry.getKey(), entry.getValue()));
        configuration.streamForIos().forEach(entry -> setForIos(entry.getKey(), entry.getValue()));
        return this;
    }

    public MobileLocatorConfiguration setFromIfNotPresent(@NotNull MobileLocatorConfiguration configuration) {
        configuration.streamForAndroid().forEach(entry -> setForAndroidIfNotPresent(entry.getKey(), entry.getValue()));
        configuration.streamForIos().forEach(entry -> setForIosIfNotPresent(entry.getKey(), entry.getValue()));
        return this;
    }

    public MobileLocatorHolder getAndroidLocator(@NotNull String locatorName) {
        return Optional.ofNullable(androidLocatorHolders.get(locatorName))
                .orElseThrow(() -> LocatorNotFound.exception(ELEMENT_LOCATOR_NOT_FOUND.getMessage(locatorName)));
    }

    public MobileLocatorHolder getIosLocator(@NotNull String locatorName) {
        return Optional.ofNullable(iosLocatorHolders.get(locatorName))
                .orElseThrow(() -> LocatorNotFound.exception(ELEMENT_LOCATOR_NOT_FOUND.getMessage(locatorName)));
    }

    public Map<String, MobileLocatorHolder> androidLocatorsAsMap() {
        return new HashMap<>(androidLocatorHolders);
    }

    public Map<String, MobileLocatorHolder> iosLocatorsAsMap() {
        return new HashMap<>(iosLocatorHolders);
    }

    public Stream<Entry<String, AndroidLocatorHolder>> streamForAndroid() {
        return androidLocatorHolders.entrySet().stream();
    }

    public Stream<Entry<String, IosLocatorHolder>> streamForIos() {
        return iosLocatorHolders.entrySet().stream();
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        ObjectNode androidNode = createObjectNode();
        rootNode.set("androidLocators", androidNode);
        androidLocatorHolders.forEach((name, implementation) -> androidNode.put(name, implementation.getClass().getCanonicalName()));
        ObjectNode iosNode = createObjectNode();
        rootNode.set("iosLocators", iosNode);
        iosLocatorHolders.forEach((name, implementation) -> iosNode.put(name, implementation.getClass().getCanonicalName()));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
