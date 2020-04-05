package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.TAP_METHOD;

public interface TapAvailable extends ElementMethodAvailable {

    @MappedMethod(TAP_METHOD)
    void tap();

}

