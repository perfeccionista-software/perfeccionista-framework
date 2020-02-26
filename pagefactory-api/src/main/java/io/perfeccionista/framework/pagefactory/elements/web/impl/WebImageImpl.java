package io.perfeccionista.framework.pagefactory.elements.web.impl;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.web.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.web.WebImage;
import io.perfeccionista.framework.pagefactory.elements.web.methods.SeleniumClick;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.CLICK_METHOD;

@ElementMethod(type = CLICK_METHOD, implementation = SeleniumClick.class)
public class WebImageImpl extends AbstractWebChildElement implements WebImage {

    @Override
    public OperationResult<Void> click() {
        return getMethodImplementation(CLICK_METHOD, Void.class).execute(this);
    }

}
