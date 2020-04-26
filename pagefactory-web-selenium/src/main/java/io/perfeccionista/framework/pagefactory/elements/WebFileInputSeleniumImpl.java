package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetLabel;
import io.perfeccionista.framework.pagefactory.elements.actions.JsGetText;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumIsEnabled;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumSendKeys;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumSubmit;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LABEL;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.GET_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.IS_ENABLED_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SEND_KEYS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SUBMIT_METHOD;

@ElementAction(name = CLEAR_METHOD, implementation = SeleniumClear.class)
@ElementAction(name = GET_LABEL_METHOD, implementation = JsGetLabel.class)
@ElementAction(name = GET_TEXT_METHOD, implementation = JsGetText.class)
@ElementAction(name = IS_ENABLED_METHOD, implementation = SeleniumIsEnabled.class)
@ElementAction(name = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
@ElementAction(name = SUBMIT_METHOD, implementation = SeleniumSubmit.class)
@WebLocator(component = LABEL, xpath = "preceding::label | following::label | parent::label")
public abstract class WebFileInputSeleniumImpl extends AbstractWebChildElementSeleniumImpl implements WebFileInput {

//    @Override
//    public void clear() {
//        getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
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
//    public boolean isEnabled() {
//        return getMethodImplementation(IS_ENABLED_METHOD, Boolean.class).execute(this);
//    }
//
//    @Override
//    public void sendKeys(CharSequence... keys) {
//        getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
//    }
//
//    @Override
//    public void submit() {
//        getMethodImplementation(SUBMIT_METHOD, Void.class).execute(this);
//    }

}
