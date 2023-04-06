package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.HAS_ATTRIBUTE_METHOD;

public interface WebElementAttributeAvailable extends WebChildElementBase {

    @WebMappedElementAction(HAS_ATTRIBUTE_METHOD)
    boolean hasAttribute(@NotNull String attributeName);

    @WebMappedElementAction(HAS_ATTRIBUTE_METHOD)
    boolean hasAttribute(@NotNull String componentName, @NotNull String attributeName);

    @WebMappedElementAction(HAS_ATTRIBUTE_METHOD)
    Optional<String> getAttribute(@NotNull String attributeName);

    @WebMappedElementAction(HAS_ATTRIBUTE_METHOD)
    Optional<String> getAttribute(@NotNull String componentName, @NotNull String attributeName);

}
