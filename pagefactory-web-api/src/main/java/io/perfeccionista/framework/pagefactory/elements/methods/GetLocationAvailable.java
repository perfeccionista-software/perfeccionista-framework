package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.ElementBase;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;

public interface GetLocationAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(GET_LOCATION_METHOD)
    Location getLocation(String componentName);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_LOCATION_METHOD)
    WebChildElement componentShouldHaveLocation(String componentName, Location expectedLocation);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_LOCATION_METHOD)
    WebChildElement componentShouldNotHaveLocation(String componentName, Location expectedLocation);

}
