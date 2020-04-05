package io.perfeccionista.framework.pagefactory.elements.methods.availability;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethodAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.MappedMethod;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;

public interface SendKeysAvailable extends ElementMethodAvailable {

    @MappedMethod(SEND_KEYS_METHOD)
    SendKeysAvailable sendKeys(CharSequence... keys);

}
