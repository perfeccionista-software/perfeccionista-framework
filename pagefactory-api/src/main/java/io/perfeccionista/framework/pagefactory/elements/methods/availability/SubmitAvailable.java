package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SUBMIT_METHOD;

public interface SubmitAvailable extends ElementMethodAvailable {

    @MappedMethod(SUBMIT_METHOD)
    SubmitAvailable submit();

}
