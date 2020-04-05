package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.HOVER_TO_METHOD;

public interface HoverToAvailable extends ElementMethodAvailable {

    @MappedMethod(HOVER_TO_METHOD)
    HoverToAvailable hoverTo(boolean withOutOfBounds);

}
