package io.perfeccionista.framework.pagefactory.elements.web;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SendKeysAvailable;

public interface WebAutocomplete extends WebDropDownList,
        SendKeysAvailable, ClearAvailable {
}
