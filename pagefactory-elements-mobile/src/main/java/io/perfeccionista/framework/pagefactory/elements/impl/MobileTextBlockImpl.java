package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractMobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.MobileTextBlock;
import io.perfeccionista.framework.pagefactory.elements.methods.AppiumGetText;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;

@ElementMethod(type = GET_TEXT_METHOD, implementation = AppiumGetText.class)
public abstract class MobileTextBlockImpl extends AbstractMobileChildElement implements MobileTextBlock {
//
//    @Override
//    public OperationResult<String> getText() {
//        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
//    }

}
