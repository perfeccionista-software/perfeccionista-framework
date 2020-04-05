package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;

public interface ClickAvailable extends ElementMethodAvailable {

    @MappedMethod(CLICK_METHOD)
    ClickAvailable click();

}
