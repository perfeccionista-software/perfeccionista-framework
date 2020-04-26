package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetText;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_TEXT_METHOD;

@ElementAction(name = GET_TEXT_METHOD, implementation = JsGetText.class)

public abstract class WebTextBlockSeleniumImpl extends AbstractWebChildElementSeleniumImpl implements WebTextBlock {
//
//    @Override
//    public String getText() {
//        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
//    }

}
