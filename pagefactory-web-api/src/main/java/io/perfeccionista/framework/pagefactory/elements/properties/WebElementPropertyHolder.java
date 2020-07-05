package io.perfeccionista.framework.pagefactory.elements.properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementPropertyHolder {

    private final String name;
    private final WebLocatorHolder locatorHolder;
    private final WebElementPropertyExtractor<WebChildElement> propertyExtractor;

    private WebElementPropertyHolder(String name,
                                     WebLocatorHolder locatorHolder,
                                     WebElementPropertyExtractor<WebChildElement> propertyExtractor) {
        this.name = name;
        this.locatorHolder = locatorHolder;
        this.propertyExtractor = propertyExtractor;
    }

    public static WebElementPropertyHolder of(@NotNull String name,
                                              @Nullable WebLocatorHolder locatorHolder,
                                              @NotNull WebElementPropertyExtractor<WebChildElement> propertyExtractor) {
        return new WebElementPropertyHolder(name, locatorHolder, propertyExtractor);
    }

    public String getName() {
        return name;
    }

    public Optional<WebLocatorHolder> getLocatorHolder() {
        return Optional.ofNullable(locatorHolder);
    }

    public String extractPropertyValue(WebChildElement element) {
        return propertyExtractor.extract(element, getLocatorHolder());
    }

    public WebElementPropertyExtractor<WebChildElement> getPropertyExtractor() {
        return propertyExtractor;
    }

    public JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", name);
        rootNode.set("locator", locatorHolder == null ? null : locatorHolder.toJson());
        rootNode.put("extractor", propertyExtractor.getClass().getCanonicalName());
        return rootNode;
    }
}
