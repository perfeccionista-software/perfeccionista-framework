package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_HAVE_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_HAVE_BOUNDS_METHOD;

public interface GetBoundsAvailable extends Element {

    @MappedElementAction(GET_BOUNDS_METHOD)
    Bounds getBounds(String componentName);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_BOUNDS_METHOD)
    GetBoundsAvailable componentShouldHaveBounds(String componentName, Bounds bounds);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_BOUNDS_METHOD)
    GetBoundsAvailable componentShouldNotHaveBounds(String componentName, Bounds bounds);

}