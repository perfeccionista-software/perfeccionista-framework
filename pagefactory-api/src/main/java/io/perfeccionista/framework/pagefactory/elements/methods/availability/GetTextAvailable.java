package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_HAVE_TEXT_METHOD;

public interface GetTextAvailable extends ElementMethodAvailable {

    @MappedMethod(GET_TEXT_METHOD)
    String getText();

    @MappedMethod(SHOULD_HAVE_TEXT_METHOD)
    GetTextAvailable shouldHaveText(StringValue stringValue);

}
