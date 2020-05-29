package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;
import io.perfeccionista.framework.plugin.AssertMethodType;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_HAVE_LOCATION_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_HAVE_LOCATION_METHOD;

public interface GetLocationAvailable extends Element {

    @MappedElementAction(GET_LOCATION_METHOD)
    Location getLocation(String componentName);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_LOCATION_METHOD)
    GetDimensionsAvailable componentShouldHaveLocation(String componentName, Location location);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_LOCATION_METHOD)
    GetDimensionsAvailable componentShouldNotHaveLocation(String componentName, Location location);

}
