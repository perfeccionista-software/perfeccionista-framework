package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;

public interface GetLocationAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(GET_LOCATION_METHOD)
    @NotNull Location getLocation(@NotNull String componentName);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_LOCATION_METHOD)
    WebChildElement componentShouldHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_LOCATION_METHOD)
    WebChildElement componentShouldNotHaveLocation(@NotNull String componentName, @NotNull Location expectedLocation);

}
