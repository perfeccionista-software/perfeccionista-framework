package io.perfeccionista.framework.pagefactory.elements.locators;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileLocatorRegistry {

    protected final Map<String, MobileLocatorHolder> mobileLocators;

    private MobileLocatorRegistry(Map<String, MobileLocatorHolder> mobileLocators) {
        this.mobileLocators = mobileLocators;
    }

    public static MobileLocatorRegistry empty() {
        return new MobileLocatorRegistry(new HashMap<>());
    }

    public static MobileLocatorRegistry of(Map<String, MobileLocatorHolder> mobileLocators) {
        return new MobileLocatorRegistry(new HashMap<>(mobileLocators));
    }

    public MobileLocatorRegistry addLocator(@NotNull String rootLocator, @NotNull MobileLocatorHolder locator) {
        mobileLocators.put(rootLocator, locator);
        return this;
    }

    /**
     * Во многих сценариях нам необходимо будет модифицировать заданный ранее локатор (добавить индекс),
     * поэтому необходимо возвращать его клон, а не оригинальный инстанс
     * @param locatorName
     * @return
     */
    public Optional<MobileLocatorHolder> getOptionalLocator(String locatorName) {
        MobileLocatorHolder locatorHolder = null;
        locatorHolder = mobileLocators.get(locatorName);
        if (null == locatorHolder) {
            return Optional.empty();
        }
        return Optional.of(locatorHolder.clone());
    }

    public Stream<Entry<String, MobileLocatorHolder>> stream() {
        return mobileLocators.entrySet().stream();
    }

    public void forEach(BiConsumer<String, MobileLocatorHolder> consumer) {
        mobileLocators.forEach(consumer);
    }

    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        this.mobileLocators.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(entry -> rootNode.set(entry.getKey(), entry.getValue().toJson()));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
