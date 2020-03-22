package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.WebSimpleAutocomplete;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;


public abstract class WebSimpleAutocompleteImpl extends WebSimpleDropDownListImpl implements WebSimpleAutocomplete {

    @Override
    public void clear() {
        getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
    }

    @Override
    public void sendKeys(CharSequence... keys) {
        getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
    }

}
