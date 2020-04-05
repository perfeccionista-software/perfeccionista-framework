package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;
import io.perfeccionista.framework.value.string.StringValue;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SHOULD_HAVE_LABEL_METHOD;

public interface GetLabelAvailable extends ElementMethodAvailable {

    @MappedMethod(GET_LABEL_METHOD)
    String getLabel();

    @MappedMethod(SHOULD_HAVE_LABEL_METHOD)
    GetLabelAvailable shouldHaveLabel(StringValue stringValue);

}
