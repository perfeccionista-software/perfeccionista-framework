package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.WebSimpleDropDownList;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.UL;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLOSE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.IS_DISPLAYED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.OPEN_METHOD;


public abstract class WebSimpleDropDownListImpl extends WebSimpleUnorderedListImpl implements WebSimpleDropDownList {

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
    public boolean isOpen() {
        return getMethodImplementation(IS_DISPLAYED_METHOD, Boolean.class).execute(this, UL);
    }

    @Override
    public void open() {
        getMethodImplementation(OPEN_METHOD, Void.class).execute(this);
    }

    @Override
    public void close() {
        getMethodImplementation(CLOSE_METHOD, Void.class).execute(this);
    }

}
