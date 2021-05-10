package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.emulator.keys.KeyEventsChain;
import io.perfeccionista.framework.plugin.ActionMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.WebMappedElementAction;
import org.jetbrains.annotations.NotNull;

import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.CLEAR_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.REPLACE_TEXT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.SEND_KEY_EVENTS_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.ElementActionNames.TYPE_TEXT_METHOD;

public interface WebInputTextAvailable extends WebChildElement {

    @ActionMethodType
    @WebMappedElementAction(CLEAR_METHOD)
    WebInputTextAvailable clear();

    @ActionMethodType
    @WebMappedElementAction(TYPE_TEXT_METHOD)
    WebInputTextAvailable typeText(@NotNull String keys);

    @ActionMethodType
    @WebMappedElementAction(REPLACE_TEXT_METHOD)
    WebInputTextAvailable replaceText(@NotNull String keys);

    @ActionMethodType
    @WebMappedElementAction(SEND_KEY_EVENTS_METHOD)
    WebInputTextAvailable sendKeyEvents(@NotNull KeyEventsChain keyEvents);

}
