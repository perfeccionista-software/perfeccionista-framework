package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileImage;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumTap;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.TAP_METHOD;

@ElementMethod(type = TAP_METHOD, implementation = AppiumTap.class)
public class MobileImageImpl extends AbstractMobileChildElement implements MobileImage {

    @Override
    public OperationResult<Void> tap() {
        return getMethodImplementation(TAP_METHOD, Void.class).execute(this);
    }

}
