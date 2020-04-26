package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SEND_KEYS_METHOD;

public interface SendKeysAvailable extends Element {

    @ActionMethodType
    @MappedElementAction(SEND_KEYS_METHOD)
    SendKeysAvailable sendKeys(CharSequence... keys);

}
