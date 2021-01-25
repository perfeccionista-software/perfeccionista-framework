package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.methods.WebGetElementBoundsAvailableMatcher;
import io.perfeccionista.framework.pagefactory.elements.ElementBounds;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.GET_ELEMENT_BOUNDS_METHOD;

public interface WebGetElementBoundsAvailable extends WebChildElementBase {

    @WebMappedElementAction(GET_ELEMENT_BOUNDS_METHOD)
    @NotNull ElementBounds getElementBounds();

    @WebMappedElementAction(GET_ELEMENT_BOUNDS_METHOD)
    @NotNull ElementBounds getElementBounds(@NotNull String componentName);

    @AssertMethodType
    WebGetElementBoundsAvailable should(@NotNull WebGetElementBoundsAvailableMatcher matcher);

}
