package io.perfeccionista.framework.pagefactory.elements.states.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.STATE;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementStateHolder implements JsonSerializable {

    private final String name;
    private final WebLocatorHolder locatorHolder;
    private final WebElementStateExtractor stateExtractor;

    private WebElementStateHolder(String name,
                                  WebLocatorHolder locatorHolder,
                                  WebElementStateExtractor stateExtractor) {
        this.name = name;
        this.locatorHolder = locatorHolder;
        this.stateExtractor = stateExtractor;
    }

    public static WebElementStateHolder of(@NotNull String name,
                                           @Nullable WebLocatorHolder locatorHolder,
                                           @NotNull WebElementStateExtractor stateExtractor) {
        return new WebElementStateHolder(name, locatorHolder, stateExtractor);
    }

    public String getName() {
        return name;
    }

    public Optional<WebLocatorHolder> getLocatorHolder() {
        return Optional.ofNullable(locatorHolder);
    }

    public WebElementStateExtractor getStateExtractor() {
        return stateExtractor;
    }

    public WebElementOperation<Boolean> getOperation(WebChildElementBase element) {
        return stateExtractor.getOperation(element, getLocatorHolder());
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", name);
        rootNode.set("locator", locatorHolder == null ? null : locatorHolder.setLocatorComponent(STATE).toJson());
        rootNode.put("extractor", stateExtractor.getClass().getCanonicalName());
        return rootNode;
    }

}
