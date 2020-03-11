package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileLink;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumGetText;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumTap;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.TAP_METHOD;

@ElementMethod(type = TAP_METHOD, implementation = AppiumTap.class)
@ElementMethod(type = GET_TEXT_METHOD, implementation = AppiumGetText.class)
public class MobileLinkImpl extends AbstractMobileChildElement implements MobileLink {

    @Override
    public OperationResult<String> getText() {
        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
    }

    @Override
    public OperationResult<Void> tap() {
        return getMethodImplementation(TAP_METHOD, Void.class).execute(this);
    }

}
