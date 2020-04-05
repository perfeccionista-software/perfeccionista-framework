package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_OPEN_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_BE_CLOSED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_BE_OPEN_METHOD;

public interface IsOpenAvailable extends ElementMethodAvailable {

    @MappedMethod(IS_OPEN_METHOD)
    boolean isOpen();

    @MappedMethod(SHOULD_BE_OPEN_METHOD)
    IsOpenAvailable shouldBeOpen();

    @MappedMethod(SHOULD_BE_CLOSED_METHOD)
    IsOpenAvailable shouldBeClosed();

}
