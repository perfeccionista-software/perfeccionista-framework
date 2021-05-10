package io.perfeccionista.framework.pagefactory.elements.properties.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.PROPERTY;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class MobileElementPropertyHolder implements JsonSerializable {

    private final String name;
    private final MobileLocatorHolder locatorHolder;
    private final MobileElementPropertyExtractor propertyExtractor;

    private MobileElementPropertyHolder(String name,
                                     MobileLocatorHolder locatorHolder,
                                     MobileElementPropertyExtractor propertyExtractor) {
        this.name = name;
        this.locatorHolder = locatorHolder;
        this.propertyExtractor = propertyExtractor;
    }

    public static MobileElementPropertyHolder of(@NotNull String name,
                                              @Nullable MobileLocatorHolder locatorHolder,
                                              @NotNull MobileElementPropertyExtractor propertyExtractor) {
        return new MobileElementPropertyHolder(name, locatorHolder, propertyExtractor);
    }

    public String getName() {
        return name;
    }

    public Optional<MobileLocatorHolder> getLocatorHolder() {
        return Optional.ofNullable(locatorHolder);
    }

    public MobileElementPropertyExtractor getPropertyExtractor() {
        return propertyExtractor;
    }

    public MobileElementOperation<String> getOperation(MobileChildElementBase element) {
        return propertyExtractor.getOperation(element, getLocatorHolder());
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", name);
        rootNode.set("locator", locatorHolder == null ? null : locatorHolder.setLocatorComponent(PROPERTY).toJson());
        rootNode.put("extractor", propertyExtractor.getClass().getCanonicalName());
        return rootNode;
    }

}
