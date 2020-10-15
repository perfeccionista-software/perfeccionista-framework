package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.matcher.actions.GetLocationAvailableMatcher;
import io.perfeccionista.framework.measurements.Location;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.GET_LOCATION_METHOD;

public interface GetLocationAvailable extends WebChildElementBase {

    @WebMappedElementAction(GET_LOCATION_METHOD)
    @NotNull Location getLocation(@NotNull String componentName);

    @AssertMethodType
    GetLocationAvailable should(@NotNull GetLocationAvailableMatcher matcher);

}
