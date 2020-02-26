package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.ClearAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SendKeysAvailable;
import io.perfeccionista.framework.pagefactory.itemfilter.Filter;

public interface SimpleAutocomplete<F extends Filter<?, ?>> extends SimpleDropDownList<F>,
        SendKeysAvailable, ClearAvailable {
}
