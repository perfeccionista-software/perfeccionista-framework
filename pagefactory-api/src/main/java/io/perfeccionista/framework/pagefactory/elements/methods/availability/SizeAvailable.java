package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;
import io.perfeccionista.framework.value.number.NumberValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_HAVE_SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;

public interface SizeAvailable extends ElementMethodAvailable {

    @MappedMethod(SIZE_METHOD)
    int size();

    @MappedMethod(SHOULD_HAVE_SIZE_METHOD)
    SizeAvailable shouldHaveSize(NumberValue<Integer> integerValue);

}
