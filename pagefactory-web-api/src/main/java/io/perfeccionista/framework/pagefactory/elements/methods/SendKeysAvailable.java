package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SEND_KEYS_METHOD;

public interface SendKeysAvailable extends WebLocatorChainAvailable {

    @ActionMethodType
    @MappedElementAction(SEND_KEYS_METHOD)
    WebChildElement sendKeys(CharSequence... keys);

}