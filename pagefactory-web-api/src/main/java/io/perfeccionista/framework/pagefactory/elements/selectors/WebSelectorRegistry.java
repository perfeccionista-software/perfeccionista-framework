package io.perfeccionista.framework.pagefactory.elements.selectors;

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

public class WebSelectorRegistry {

    // Элемент инициализируется только одним типом локатора (web/ios/android)
    protected final Map<String, WebSelectorHolder> selectors;

    private WebSelectorRegistry(Map<String, WebSelectorHolder> selectors) {
        this.selectors = selectors;
    }

    public static WebSelectorRegistry empty() {
        return new WebSelectorRegistry(new HashMap<>());
    }

    public static WebSelectorRegistry of(Map<String, WebSelectorHolder> selectors) {
        return new WebSelectorRegistry(selectors);
    }

    public WebSelectorRegistry addSelector(@NotNull String componentName, @NotNull WebSelectorHolder selector) {
        selectors.put(componentName, selector);
        return this;
    }

    /**
     * Во многих сценариях нам необходимо будет модифицировать заданный ранее локатор (добавить индекс),
     * поэтому необходимо возвращать его клон, а не оригинальный инстанс
     * @param selectorName
     * @return
     */
    public Optional<WebSelectorHolder> getOptionalSelector(String selectorName) {
        WebSelectorHolder selectorHolder = selectors.get(selectorName);
        if (null == selectorHolder) {
            return Optional.empty();
        }
        return Optional.of(selectorHolder.clone());
    }

    public Stream<Entry<String, WebSelectorHolder>> stream() {
        return selectors.entrySet().stream();
    }

    public void forEach(BiConsumer<String, WebSelectorHolder> consumer) {
        selectors.forEach(consumer);
    }

    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();
        this.selectors.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(entry -> rootNode.set(entry.getKey(), entry.getValue().toJson()));
        return rootNode;
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
