package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_BOUNDS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_HAVE_BOUNDS_METHOD;

public interface GetBoundsAvailable extends ElementMethodAvailable {

    @MappedMethod(GET_BOUNDS_METHOD)
    Bounds getBounds();

    @MappedMethod(SHOULD_HAVE_BOUNDS_METHOD)
    GetBoundsAvailable shouldHaveBounds(Bounds bounds);

}