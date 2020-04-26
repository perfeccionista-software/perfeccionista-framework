package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClick;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.CLICK_METHOD;

@ElementAction(name = CLICK_METHOD, implementation = SeleniumClick.class)
public abstract class WebImageSeleniumImpl extends AbstractWebChildElementSeleniumImpl implements WebImage {
//
//    @Override
//    public void click() {
//        getMethodImplementation(CLICK_METHOD, Void.class).execute(this);
//    }

}
