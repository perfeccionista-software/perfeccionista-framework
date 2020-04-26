package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumClear;
import io.perfeccionista.framework.pagefactory.elements.actions.SeleniumSendKeys;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SEND_KEYS_METHOD;

@ElementAction(name = CLEAR_METHOD, implementation = SeleniumClear.class)
@ElementAction(name = SEND_KEYS_METHOD, implementation = SeleniumSendKeys.class)
public abstract class WebAutocompleteSeleniumImpl extends WebDropDownListSeleniumImpl implements WebAutocomplete {
//
//    @Override
//    public void clear() {
//        getMethodImplementation(CLEAR_METHOD, Void.class).execute(this);
//    }
//
//    @Override
//    public void sendKeys(@NotNull CharSequence... keys) {
//        getMethodImplementation(SEND_KEYS_METHOD, Void.class).execute(this, keys);
//    }

}
