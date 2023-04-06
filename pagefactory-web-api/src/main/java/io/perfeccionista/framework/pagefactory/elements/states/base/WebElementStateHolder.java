package io.perfeccionista.framework.pagefactory.elements.states.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.STATE;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementStateHolder implements JsonSerializable {

    private final String name;
    private final WebSelectorHolder selectorHolder;
    private final WebElementStateExtractor stateExtractor;

    private WebElementStateHolder(String name,
                                  WebSelectorHolder selectorHolder,
                                  WebElementStateExtractor stateExtractor) {
        this.name = name;
        this.selectorHolder = selectorHolder;
        this.stateExtractor = stateExtractor;
    }

    public static WebElementStateHolder of(@NotNull String name,
                                           @Nullable WebSelectorHolder selectorHolder,
                                           @NotNull WebElementStateExtractor stateExtractor) {
        return new WebElementStateHolder(name, selectorHolder, stateExtractor);
    }

    public String getName() {
        return name;
    }

    public Optional<WebSelectorHolder> getSelectorHolder() {
        return Optional.ofNullable(selectorHolder);
    }

    public WebElementStateExtractor getStateExtractor() {
        return stateExtractor;
    }

    public WebElementOperation<Boolean> getOperation(WebChildElement element) {
        return stateExtractor.getOperation(element, getSelectorHolder());
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", name);
        rootNode.set("selector", selectorHolder == null ? null : selectorHolder.setSelectorComponent(STATE).toJson());
        rootNode.put("extractor", stateExtractor.getClass().getCanonicalName());
        return rootNode;
    }

}
