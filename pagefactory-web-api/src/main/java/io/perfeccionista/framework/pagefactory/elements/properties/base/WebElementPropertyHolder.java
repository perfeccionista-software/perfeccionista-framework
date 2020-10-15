package io.perfeccionista.framework.pagefactory.elements.properties.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.JsOperation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.PROPERTY;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementPropertyHolder {

    private final String name;
    private final WebLocatorHolder locatorHolder;
    private final WebElementPropertyExtractor propertyExtractor;

    private WebElementPropertyHolder(String name,
                                     WebLocatorHolder locatorHolder,
                                     WebElementPropertyExtractor propertyExtractor) {
        this.name = name;
        this.locatorHolder = locatorHolder;
        this.propertyExtractor = propertyExtractor;
    }

    public static WebElementPropertyHolder of(@NotNull String name,
                                              @Nullable WebLocatorHolder locatorHolder,
                                              @NotNull WebElementPropertyExtractor propertyExtractor) {
        return new WebElementPropertyHolder(name, locatorHolder, propertyExtractor);
    }

    public String getName() {
        return name;
    }

    public Optional<WebLocatorHolder> getLocatorHolder() {
        return Optional.ofNullable(locatorHolder);
    }

    public WebElementPropertyExtractor getPropertyExtractor() {
        return propertyExtractor;
    }

    public JsOperation<String> getJsOperation(WebChildElementBase element) {
        return propertyExtractor.getJsOperation(element, getLocatorHolder());
    }

    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", name);
        rootNode.set("locator", locatorHolder == null ? null : locatorHolder.setLocatorComponent(PROPERTY).toJson());
        rootNode.put("extractor", propertyExtractor.getClass().getCanonicalName());
        return rootNode;
    }
}
