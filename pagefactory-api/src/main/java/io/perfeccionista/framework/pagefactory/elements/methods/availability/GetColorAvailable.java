package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;
import io.perfeccionista.framework.plugin.Color;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_COLOR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_HAVE_COLOR_METHOD;

public interface GetColorAvailable extends ElementMethodAvailable {

    @MappedMethod(GET_COLOR_METHOD)
    Color getColor(String component);

    @MappedMethod(SHOULD_HAVE_COLOR_METHOD)
    GetColorAvailable shouldHaveColor(String component, Color color);

}
