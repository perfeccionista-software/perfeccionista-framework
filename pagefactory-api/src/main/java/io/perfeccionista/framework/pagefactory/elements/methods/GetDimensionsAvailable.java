package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_HAVE_DIMENSIONS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SHOULD_NOT_HAVE_DIMENSIONS_METHOD;

public interface GetDimensionsAvailable extends Element {

    @MappedElementAction(GET_DIMENSIONS_METHOD)
    Dimensions getDimensions(String componentName);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_DIMENSIONS_METHOD)
    GetDimensionsAvailable componentShouldHaveDimensions(String componentName, Dimensions dimensions);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_DIMENSIONS_METHOD)
    GetDimensionsAvailable componentShouldNotHaveDimensions(String componentName, Dimensions dimensions);

}