package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.methods.WebElementPropertyAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementPropertyHolder;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_PROPERTY_VALUE_METHOD;

/**
 * TODO JavaDoc
 */
public interface WebElementPropertyAvailable extends WebChildElementBase {

    Optional<WebElementPropertyHolder> getProperty(String propertyName);

    @WebMappedElementAction(GET_PROPERTY_VALUE_METHOD)
    @Nullable String getPropertyValue(@NotNull String propertyName);

    @AssertMethodType
    WebChildElementBase should(@NotNull WebElementPropertyAvailableMatcher matcher);

}
