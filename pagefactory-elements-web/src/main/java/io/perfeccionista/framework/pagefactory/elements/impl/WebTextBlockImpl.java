package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.methods.ElementMethod;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.methods.JsGetText;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.GET_TEXT_METHOD;

@ElementMethod(type = GET_TEXT_METHOD, implementation = JsGetText.class)
public class WebTextBlockImpl extends AbstractWebChildElement implements WebTextBlock {

    @Override
    public OperationResult<String> getText() {
        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
    }

}
