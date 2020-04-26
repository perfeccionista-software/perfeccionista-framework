package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClick;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumSendKeys;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.CLICK_METHOD;

@ElementAction(name = CLEAR_METHOD, implementation = SeleniumClear.class)
@ElementAction(name = CLICK_METHOD, implementation = SeleniumClick.class)
@ElementAction(name = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementAction(name = GET_TEXT_METHOD, implementation = JsGetText.class)
@ElementAction(name = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
public abstract class WebTextInputSeleniumImpl extends AbstractWebChildElementSeleniumImpl implements WebTextInput {

//    @Override
//    public void clear() {
//        getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public void click() {
//        getMethodImplementation(CLICK_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public String getLabel() {
//        return getMethodImplementation(GET_LABEL_METHOD, String.class).execute(this);
//    }
//
//    @Override
//    public String getText() {
//        return getMethodImplementation(GET_TEXT_METHOD, String.class).execute(this);
//    }
//
//    @Override
//    public void sendKeys(CharSequence... keys) {
//        getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
