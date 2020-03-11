package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SendKeysAvailable;

public interface WebSimpleAutocomplete extends WebSimpleDropDownList,
        SendKeysAvailable, ClearAvailable {
}
