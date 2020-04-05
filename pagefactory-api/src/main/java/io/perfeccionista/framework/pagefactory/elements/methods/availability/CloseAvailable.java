package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLOSE_METHOD;

public interface CloseAvailable extends ElementMethodAvailable {

    @MappedMethod(CLOSE_METHOD)
    CloseAvailable close();

}
