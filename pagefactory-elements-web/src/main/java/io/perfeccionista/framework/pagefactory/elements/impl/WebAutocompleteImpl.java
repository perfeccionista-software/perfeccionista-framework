package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.WebAutocomplete;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.methods.SeleniumSendKeys;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;


public abstract class WebAutocompleteImpl extends WebDropDownListImpl implements WebAutocomplete {
//
//    @Override
//    public void clear() {
//        getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public void sendKeys(@NotNull CharSequence... keys) {
//        getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
//    }

}
