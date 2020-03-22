package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;

public abstract class WebTextBlockImpl extends AbstractWebChildElement implements WebTextBlock {

    @Override
    public String getText() {
        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
    }

}
