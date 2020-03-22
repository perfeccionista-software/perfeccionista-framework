package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTextInput;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SEND_KEYS_METHOD;


public abstract class WebTextInputImpl extends AbstractWebChildElement implements WebTextInput {

    @Override
    public void clear() {
        getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
    }

    @Override
    public void click() {
        getMethodImplementation(CLICK_METHOD, Void.class).execute(this);
    }

    @Override
    public String getLabel() {
        return getMethodImplementation(GET_LABEL_METHOD, String.class).execute(this);
    }

    @Override
    public String getText() {
        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
    }

    @Override
    public void sendKeys(CharSequence... keys) {
        getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
