package io.perfeccionista.framework.pagefactory.elements.states.base;

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

public class MobileElementStateHolder implements JsonSerializable {

    private final String name;
    private final MobileLocatorHolder locatorHolder;
    private final MobileElementStateExtractor stateExtractor;

    private MobileElementStateHolder(String name,
                                     MobileLocatorHolder locatorHolder,
                                     MobileElementStateExtractor stateExtractor) {
        this.name = name;
        this.locatorHolder = locatorHolder;
        this.stateExtractor = stateExtractor;
    }

    public static MobileElementStateHolder of(@NotNull String name,
                                              @Nullable MobileLocatorHolder locatorHolder,
                                              @NotNull MobileElementStateExtractor stateExtractor) {
        return new MobileElementStateHolder(name, locatorHolder, stateExtractor);
    }

    public String getName() {
        return name;
    }

    public Optional<MobileLocatorHolder> getLocatorHolder() {
        return Optional.ofNullable(locatorHolder);
    }

    public MobileElementStateExtractor getStateExtractor() {
        return stateExtractor;
    }

    public MobileElementOperation<Boolean> getOperation(MobileChildElementBase element) {
        return stateExtractor.getOperation(element, getLocatorHolder());
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", name);
        rootNode.set("locator", locatorHolder == null ? null : locatorHolder.setLocatorComponent(PROPERTY).toJson());
        rootNode.put("extractor", stateExtractor.getClass().getCanonicalName());
        return rootNode;
    }

}
