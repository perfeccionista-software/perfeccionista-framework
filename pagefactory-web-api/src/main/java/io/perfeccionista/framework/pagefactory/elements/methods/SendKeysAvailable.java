package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElementBase;
import io.perfeccionista.framework.pagefactory.keys.KeysEventChain;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.base.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SEND_KEYS_EVENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.actions.WebElementActionNames.SEND_KEYS_METHOD;

public interface SendKeysAvailable extends WebChildElementBase {

    @ActionMethodType
    @WebMappedElementAction(SEND_KEYS_METHOD)
    SendKeysAvailable sendKeys(@NotNull String keys);

    @ActionMethodType
    @WebMappedElementAction(SEND_KEYS_EVENTS_METHOD)
    SendKeysAvailable sendKeys(@NotNull KeysEventChain keyEvents);

}